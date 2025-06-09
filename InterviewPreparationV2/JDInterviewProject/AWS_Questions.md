# AWS Interview Questions

These questions cover foundational AWS knowledge relevant for deploying and operating applications in the cloud.

## Questions and Answers

### 1. Describe the difference between EC2 and Lambda.
**Answer:** EC2 provides virtual servers that you manage and scale as needed. Lambda is a serverless compute service that runs code in response to events without provisioning servers. EC2 offers full control, whereas Lambda abstracts infrastructure management.

### 2. What is IAM and why is it important?
**Answer:** Identity and Access Management (IAM) controls permissions for AWS resources. It is critical for enforcing least-privilege access by defining users, groups, and roles with specific policies.

### 3. How do you secure data at rest in AWS?
**Answer:** Use encryption features such as S3 server-side encryption or EBS volume encryption. For databases like RDS, enable encryption at rest and manage keys with AWS KMS.

### 4. Explain Auto Scaling.
**Answer:** Auto Scaling adjusts the number of EC2 instances based on demand. Scaling policies can launch or terminate instances automatically when metrics like CPU utilization cross defined thresholds.

### 5. What is a common use case for Amazon S3?
**Answer:** S3 is ideal for storing static assets such as images, backups, and log files due to its high durability and scalability.
