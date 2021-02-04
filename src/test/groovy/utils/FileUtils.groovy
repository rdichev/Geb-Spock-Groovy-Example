package utils

class FileUtils {
    static getTestFile(fileName) {
        return "${getDownloadFolder()}/TestFiles/${fileName}"
    }

    static getDownloadFolder() {
        return "${System.getProperty("user.dir")}/src/test/resources"
    }

    static downloadTestFiles(bucketName) {
        File file = new File("${getDownloadFolder()}/TestFiles")
        if(!file.exists() || !(file.list().length > 0)) {
            S3Utils.downloadS3BucketToLocalDirectory(bucketName, "TestFiles", getDownloadFolder())
        } else {
            println("Test files are already downloaded!")
        }
    }
}

