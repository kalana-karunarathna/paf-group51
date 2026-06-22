# Smart Campus Facility Management System — AWS DevOps CI/CD Project

This project demonstrates a complete AWS-native DevOps workflow for a containerized Smart Campus Facility Management System.

The application is built with a Spring Boot backend, React frontend, PostgreSQL database, Docker, Kubernetes, AWS CodePipeline, AWS CodeBuild, Amazon ECR, Amazon EKS, Amazon RDS, AWS Application Load Balancer, SonarQube Cloud, Trivy, and CloudWatch.

## Project Overview

The Smart Campus Facility Management System helps manage campus resources, bookings, tickets, maintenance requests, and facility operations.

This project was enhanced from a normal full-stack application into a cloud-ready DevOps project with automated build, code quality analysis, security scanning, image publishing, Kubernetes deployment, public access, database integration, and monitoring.

## Tech Stack

### Application

* Frontend: React, Vite, Nginx
* Backend: Spring Boot, Java 17, Maven
* Database: PostgreSQL
* Containerization: Docker, Docker Compose
* Orchestration: Kubernetes on Amazon EKS

### DevOps and AWS Services

* AWS CodePipeline
* AWS CodeBuild
* Amazon ECR
* Amazon EKS
* Amazon RDS PostgreSQL
* AWS Application Load Balancer
* AWS Load Balancer Controller
* AWS CloudWatch Logs
* AWS CloudWatch Dashboard
* SonarQube Cloud
* Trivy

## CI/CD Pipeline

The CI/CD pipeline is fully automated.

A push to the `dev` branch automatically triggers AWS CodePipeline.

```text
git push origin dev
→ AWS CodePipeline
→ Source stage
→ CodeBuild build stage
→ SonarQube Cloud code quality scan
→ Docker image build
→ Trivy image vulnerability scan
→ Push images to Amazon ECR
→ CodeBuild deploy stage
→ Deploy to Amazon EKS
→ Application exposed through AWS ALB
```

## Architecture

```text
Developer
  |
  | git push origin dev
  v
GitHub Repository
  |
  v
AWS CodePipeline
  |
  +--> Source Stage
  |
  +--> Build Stage: AWS CodeBuild
  |       - Backend Maven build
  |       - SonarQube Cloud static code analysis
  |       - Frontend production build
  |       - Docker image build
  |       - Trivy image security scan
  |       - Push images to Amazon ECR
  |
  +--> Deploy Stage: AWS CodeBuild
          - Configure kubectl
          - Apply Kubernetes manifests
          - Restart Kubernetes deployments
          - Wait for rollout status

Internet User
  |
  v
AWS Application Load Balancer
  |
  v
Kubernetes Ingress
  |
  v
Frontend Service
  |
  v
Frontend Pods
  |
  v
Backend Service
  |
  v
Backend Pods
  |
  v
Amazon RDS PostgreSQL
```

## SonarQube Cloud Integration

SonarQube Cloud is integrated into AWS CodeBuild to perform static code analysis.

It checks:

* Code quality
* Bugs
* Code smells
* Maintainability issues
* Reliability issues
* Security issues
* Duplicated code

SonarQube Cloud project:

```text
Organization: kalana-karunarathna
Project: paf-group51
Branch: dev
```

## Trivy Security Scanning

Trivy is used in the build stage to scan Docker images for vulnerabilities.

It scans:

* Operating system packages
* Application dependencies
* Container image vulnerabilities
* HIGH and CRITICAL vulnerabilities

Current scan mode reports vulnerabilities without blocking the build.

## Docker Images

Docker images are built by AWS CodeBuild and pushed to Amazon ECR.

```text
016170083143.dkr.ecr.us-east-1.amazonaws.com/campus-backend:dev
016170083143.dkr.ecr.us-east-1.amazonaws.com/campus-frontend:dev
```

## Kubernetes Deployment

The application is deployed to Amazon EKS using Kubernetes manifests.

Main namespace:

```text
smart-campus
```

Main Kubernetes components:

* Namespace
* ConfigMap
* Secret
* Backend Deployment
* Backend Service
* Frontend Deployment
* Frontend Service
* Ingress
* Readiness probes
* Liveness probes

## Database

The backend connects to Amazon RDS PostgreSQL.

```text
Database engine: PostgreSQL
Database name: smartcampus
Database identifier: smart-campus-postgres
```

Database configuration is managed using Kubernetes ConfigMap and Secret.

## Public Access

The application is exposed publicly using AWS Application Load Balancer through Kubernetes Ingress.

Traffic flow:

```text
Browser
→ AWS ALB
→ Kubernetes Ingress
→ Frontend Service
→ Frontend Pods
→ Backend Service
→ Backend Pods
→ Amazon RDS PostgreSQL
```

## Monitoring and Logging

CloudWatch Observability is enabled for the EKS cluster.

CloudWatch includes:

* CodeBuild logs
* EKS application logs
* EKS dataplane logs
* EKS host logs
* EKS performance metrics
* EKS control plane logs
* CloudWatch dashboard

Dashboard name:

```text
smart-campus-devops-dashboard
```

The dashboard monitors:

* EKS CPU usage
* EKS memory usage
* Pod restarts
* Unavailable replicas
* ALB request count
* ALB errors
* RDS CPU utilization
* RDS database connections
* CodeBuild build duration
* CodeBuild failed builds

## Important Commands

Check Kubernetes resources:

```bash
kubectl get pods -n smart-campus
kubectl get svc -n smart-campus
kubectl get ingress -n smart-campus
```

Get ALB URL:

```bash
kubectl get ingress smart-campus-ingress \
  -n smart-campus \
  -o jsonpath='{.status.loadBalancer.ingress[0].hostname}'
```

Test backend API through ALB:

```bash
curl -i http://<ALB-DNS-NAME>/api/resources
```

Check CodePipeline executions:

```bash
aws codepipeline list-pipeline-executions \
  --pipeline-name smart-campus-dev-pipeline \
  --region us-east-1 \
  --max-items 5
```

Check ECR backend image:

```bash
aws ecr describe-images \
  --repository-name campus-backend \
  --region us-east-1
```

Check ECR frontend image:

```bash
aws ecr describe-images \
  --repository-name campus-frontend \
  --region us-east-1
```

## Completed DevOps Features

* Local Docker Compose setup
* PostgreSQL database migration
* Backend Dockerfile
* Frontend Dockerfile
* Amazon ECR repositories
* AWS CodeBuild build automation
* SonarQube Cloud static code analysis
* Trivy Docker image scanning
* AWS CodePipeline automated CI/CD
* Automatic GitHub dev branch trigger
* Amazon EKS Kubernetes deployment
* Kubernetes ConfigMap and Secret
* Kubernetes readiness and liveness probes
* AWS Load Balancer Controller
* AWS ALB Ingress public access
* Amazon RDS PostgreSQL integration
* CloudWatch logs
* CloudWatch monitoring dashboard

## Final DevOps Flow

```text
git push origin dev
→ CodePipeline auto trigger
→ Source stage
→ CodeBuild build stage
→ SonarQube Cloud scan
→ Docker build
→ Trivy scan
→ Push images to ECR
→ CodeBuild deploy stage
→ Deploy to EKS
→ ALB serves the application
→ Backend connects to Amazon RDS
→ Logs and metrics available in CloudWatch
```

## Project Status

Completed and working successfully.
