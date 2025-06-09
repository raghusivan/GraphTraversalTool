# Infrastructure as Code Interview Questions - Advanced Set

This file lists 50 unique IaC questions with answers and code examples.

## Questions and Answers

### 1. What is the role of terraform modules?
**Answer:** Modules encapsulate common infrastructure patterns for reuse across projects.
```hcl
# terraform modules example 1
```

### 2. What is the role of cloudformation?
**Answer:** CloudFormation declaratively defines AWS resources in JSON or YAML stacks.
```hcl
# cloudformation example 2
```

### 3. What is the role of ansible playbooks?
**Answer:** Ansible playbooks describe desired server state using tasks and roles.
```hcl
# ansible playbooks example 3
```

### 4. What is the role of version control?
**Answer:** Store IaC in version control to track changes and enable collaboration.
```hcl
# version control example 4
```

### 5. What is the role of linting?
**Answer:** Variables parameterize configurations for different environments.
```hcl
# linting example 5
```

### 6. What is the role of idempotency?
**Answer:** State files record the current deployed infrastructure for tools like Terraform.
```hcl
# idempotency example 6
```

### 7. What is the role of secret management?
**Answer:** Use remote state backends to share state securely among team members.
```hcl
# secret management example 7
```

### 8. What is the role of environment reuse?
**Answer:** Immutable infrastructure means recreating resources rather than modifying them in place.
```hcl
# environment reuse example 8
```

### 9. What is the role of remote state?
**Answer:** Policy as code enforces standards using tools like Sentinel or Open Policy Agent.
```hcl
# remote state example 9
```

### 10. What is the role of backend configuration?
**Answer:** CI/CD pipelines validate and apply infrastructure code automatically.
```hcl
# backend configuration example 10
```

### 11. What is the role of module registry?
**Answer:** Providers supply plugins that interact with cloud APIs.
```hcl
# module registry example 11
```

### 12. What is the role of dynamic blocks?
**Answer:** Use environment isolation to keep dev, test, and prod configurations separate.
```hcl
# dynamic blocks example 12
```

### 13. What is the role of terraform workspaces?
**Answer:** Secrets management keeps credentials out of source control.
```hcl
# terraform workspaces example 13
```

### 14. What is the role of drift detection?
**Answer:** Idempotent operations ensure repeated runs yield the same result.
```hcl
# drift detection example 14
```

### 15. What is the role of immutability?
**Answer:** Dynamic inventories generate host lists from cloud APIs for tools like Ansible.
```hcl
# immutability example 15
```

### 16. What is the role of policy as code?
**Answer:** Outputs expose useful values, such as IP addresses, for other modules to consume.
```hcl
# policy as code example 16
```

### 17. What is the role of testing?
**Answer:** Use linting tools to enforce style and catch common errors.
```hcl
# testing example 17
```

### 18. What is the role of rollback?
**Answer:** Templating engines allow generation of configuration files dynamically.
```hcl
# rollback example 18
```

### 19. What is the role of blueprints?
**Answer:** Tagging resources helps track costs and ownership.
```hcl
# blueprints example 19
```

### 20. What is the role of multi-cloud?
**Answer:** Use Terraform workspaces to manage multiple environments from the same code base.
```hcl
# multi-cloud example 20
```

### 21. What is the role of orchestration?
**Answer:** Create reusable roles in Ansible for shared server configurations.
```hcl
# orchestration example 21
```

### 22. What is the role of variable files?
**Answer:** Remote modules can be sourced from Git or registries for easy reuse.
```hcl
# variable files example 22
```

### 23. What is the role of templating?
**Answer:** Automated testing with tools like Terratest validates IaC changes.
```hcl
# templating example 23
```

### 24. What is the role of pipeline integration?
**Answer:** Version lock providers to prevent breaking changes from new releases.
```hcl
# pipeline integration example 24
```

### 25. What is the role of state locking?
**Answer:** Use dependency graphs to determine resource creation order.
```hcl
# state locking example 25
```

### 26. What is the role of resource graph?
**Answer:** Break down monolithic templates into smaller manageable stacks.
```hcl
# resource graph example 26
```

### 27. What is the role of providers?
**Answer:** Take advantage of change sets to preview CloudFormation updates.
```hcl
# providers example 27
```

### 28. What is the role of custom plugins?
**Answer:** Use plan and apply steps to review proposed changes before execution.
```hcl
# custom plugins example 28
```

### 29. What is the role of module testing?
**Answer:** Leverage parameter stores for configuration values and secrets.
```hcl
# module testing example 29
```

### 30. What is the role of auto-approval?
**Answer:** Run Terraform in a container for consistent tooling versions.
```hcl
# auto-approval example 30
```

### 31. What is the role of deployment plans?
**Answer:** Use loops and conditionals in templates for DRY code.
```hcl
# deployment plans example 31
```

### 32. What is the role of change review?
**Answer:** Generate documentation from IaC comments automatically.
```hcl
# change review example 32
```

### 33. What is the role of infrastructure testing?
**Answer:** Implement rollback strategies for failed infrastructure deployments.
```hcl
# infrastructure testing example 33
```

### 34. What is the role of cost estimation?
**Answer:** Enable drift detection to find out-of-band changes.
```hcl
# cost estimation example 34
```

### 35. What is the role of resource tagging?
**Answer:** Secure remote state with encryption and access controls.
```hcl
# resource tagging example 35
```

### 36. What is the role of compliance?
**Answer:** Split networking, compute, and app code into separate modules.
```hcl
# compliance example 36
```

### 37. What is the role of DR automation?
**Answer:** Use data sources to reference existing infrastructure.
```hcl
# DR automation example 37
```

### 38. What is the role of security baselines?
**Answer:** Write custom providers if an API is not supported out of the box.
```hcl
# security baselines example 38
```

### 39. What is the role of modules reuse?
**Answer:** Store module versions in a registry for easier upgrades.
```hcl
# modules reuse example 39
```

### 40. What is the role of hcl syntax?
**Answer:** Implement cost estimation in pull requests.
```hcl
# hcl syntax example 40
```

### 41. What is the role of loops?
**Answer:** Apply changes in smaller batches to reduce blast radius.
```hcl
# loops example 41
```

### 42. What is the role of conditional resources?
**Answer:** Use hooks to run security scans before provisioning.
```hcl
# conditional resources example 42
```

### 43. What is the role of outputs?
**Answer:** Keep variable defaults minimal to avoid surprises.
```hcl
# outputs example 43
```

### 44. What is the role of locals?
**Answer:** Document input variables and outputs clearly for module consumers.
```hcl
# locals example 44
```

### 45. What is the role of data sources?
**Answer:** Use partial configurations with the `-target` flag for testing.
```hcl
# data sources example 45
```

### 46. What is the role of secrets rotation?
**Answer:** Automate the destruction of ephemeral environments to save costs.
```hcl
# secrets rotation example 46
```

### 47. What is the role of encryption?
**Answer:** Leverage container orchestration for running IaC tools at scale.
```hcl
# encryption example 47
```

### 48. What is the role of continuous delivery?
**Answer:** Keep provisioning logs for auditing purposes.
```hcl
# continuous delivery example 48
```

### 49. What is the role of container orchestration?
**Answer:** Use `depends_on` to manage explicit resource dependencies.
```hcl
# container orchestration example 49
```

### 50. What is the role of bootstrap scripts?
**Answer:** Combine IaC with configuration management for full server setup.
```hcl
# bootstrap scripts example 50
```

