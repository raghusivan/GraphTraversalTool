# Infrastructure as Code Interview Questions

These questions focus on practices and tools for managing infrastructure using code.

## Questions and Answers

### 1. What is Infrastructure as Code (IaC)?
**Answer:** IaC is the process of provisioning and managing infrastructure through machine-readable definition files rather than manual configuration. It enables reproducibility and version control for infrastructure resources.

### 2. Name some popular IaC tools and their typical use cases.
**Answer:** Terraform is used for cross-cloud deployments, CloudFormation for AWS-specific stacks, and Ansible for configuration management. Each tool helps automate provisioning and maintain environment consistency.

### 3. How do you test IaC templates before deployment?
**Answer:** Use validation commands such as `terraform validate` or `aws cloudformation validate-template`. Automated CI pipelines can deploy to staging environments where integration tests run before promotion to production.

### 4. Explain idempotence in the context of IaC.
**Answer:** Idempotence means applying the same configuration repeatedly yields the same infrastructure state. Tools achieve this by comparing the desired configuration to the actual state and only performing changes when needed.

### 5. What are the benefits of storing infrastructure definitions in version control?
**Answer:** Version control provides a history of changes, facilitates code reviews, and allows rollbacks. It ensures infrastructure changes follow the same processes as application code for improved collaboration.
