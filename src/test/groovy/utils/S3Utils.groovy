package utils

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.transfer.MultipleFileDownload
import com.amazonaws.services.s3.transfer.Transfer
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.amazonaws.services.s3.transfer.TransferProgress

class S3Utils extends AmazonS3Client{

    static showTransferProgress(Transfer xfer) {
        System.out.println(xfer.getDescription())
        printProgressBar(0.0)
        while ({
            try {
                Thread.sleep(100)
            } catch (InterruptedException e) {
                return
            }
            TransferProgress progress = xfer.getProgress()
            double pct = progress.getPercentTransferred()
            eraseProgressBar()
            printProgressBar(pct)
            (xfer.isDone() == false)
        } ()) continue
        // print the final state of the transfer.
        Transfer.TransferState xfer_state = xfer.getState()
        System.out.println(": " + xfer_state)
    }

    static printProgressBar(double pct) {
        // if bar_size changes, then change erase_bar (in eraseProgressBar) to
        // match.
        final int bar_size = 40
        final String empty_bar = "                                        "
        final String filled_bar = "########################################"
        int amt_full = (int)(bar_size * (pct / 100.0))
        System.out.format("  [%s%s]", filled_bar.substring(0, amt_full),
                empty_bar.substring(0, bar_size - amt_full))
    }

    static eraseProgressBar() {
        final String erase_bar = "\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"
        System.out.format(erase_bar)
    }

    static waitForCompletion(Transfer xfer)
    {
        try {
            xfer.waitForCompletion()
        } catch (AmazonServiceException e) {
            System.err.println("Amazon service error: " + e.getMessage())
            System.exit(1)
        } catch (AmazonClientException e) {
            System.err.println("Amazon client error: " + e.getMessage())
            System.exit(1)
        } catch (InterruptedException e) {
            System.err.println("Transfer interrupted: " + e.getMessage())
            System.exit(1)
        }
    }

    static downloadS3BucketToLocalDirectory(bucket_name, key_prefix, dir_path) {
        AmazonS3Client s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build()
        System.setProperty(SDKGlobalConfiguration.AWS_REGION_ENV_VAR, "eu-west-1")
        TransferManager xfer_mgr = TransferManagerBuilder.standard().withS3Client(s3).build()
        try {
            MultipleFileDownload xfer = xfer_mgr.downloadDirectory(bucket_name, key_prefix, new File(dir_path))
            showTransferProgress(xfer)
            waitForCompletion(xfer)
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage())
            System.err.println(e.getStackTrace())
            System.exit(1)
        }
    }
}
