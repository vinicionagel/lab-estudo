package br.com.labestudo.api.service;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.VersioningConfiguration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    public static final String BUCKET = "bucket-test4";

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://172.21.0.2:9000")
                            .credentials("minio", "minio123")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET).build());
                minioClient.setBucketVersioning(SetBucketVersioningArgs.builder().config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, false)).bucket(BUCKET).build());
            } else {
                System.out.println("Bucket 'bucket-test2' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(BUCKET)
                            .object("dummy.pdf")
                            .filename("/home/vinicio/Desktop/dummy.pdf")
                            .build());
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(BUCKET)
                            .object("dummy.pdf")
                            .filename("/home/vinicio/Desktop/dummy.pdf")
                            .build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
