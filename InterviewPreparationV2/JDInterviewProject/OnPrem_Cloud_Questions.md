# Building Applications On-Premise vs Cloud Interview Questions

The following questions address challenges and best practices for building applications that run both on-premise and in cloud environments.

## Questions and Answers

### 1. What factors influence the decision to deploy on-premise versus in the cloud?
**Answer:** Key factors include regulatory requirements, latency concerns, existing infrastructure investments, and cost models. Cloud offers scalability and managed services, while on-premise can provide greater control and may meet strict compliance rules.

### 2. How do you handle security considerations differently between on-premise and cloud deployments?
**Answer:** On-premise environments often rely on internal firewalls and network segmentation. In the cloud, security involves configuring identity and access management, network security groups, and using cloud provider tools to monitor activity. Both environments benefit from encryption and regular patching.

### 3. Explain a hybrid deployment strategy.
**Answer:** A hybrid strategy combines on-premise resources with cloud services. Common approaches include using VPN or direct connectivity to extend the private network to the cloud, replicating data between environments, and distributing workloads based on latency or compliance needs.

### 4. What are common challenges when migrating an application from on-premise to the cloud?
**Answer:** Challenges include re-architecting for scalability, updating authentication and authorization mechanisms, handling data transfer and synchronization, and optimizing costs. It's important to analyze dependencies and leverage cloud-native services where possible.

### 5. Describe how monitoring differs in cloud-based systems compared to on-premise systems.
**Answer:** On-premise monitoring may rely on custom scripts and local tools. In the cloud, providers offer integrated monitoring and logging services that can scale automatically. Cloud monitoring often emphasizes distributed tracing and resource-level metrics, while on-premise monitoring might focus on hardware and network usage.
