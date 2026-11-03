# DevOps Lab

Linux 환경에서 Docker, CI/CD, Terraform, Kubernetes를
직접 구축하며 학습한 DevOps 실습 프로젝트입니다.

## 기술 스택

- Linux: Ubuntu (WSL2)
- Backend: Java 17, Spring Boot
- Database: MySQL 8.4
- Reverse Proxy: Nginx
- Container: Docker, Docker Compose
- CI/CD: GitHub Actions
- Container Registry: GitHub Container Registry (GHCR)
- IaC: Terraform
- Orchestration: Kubernetes (kind)

## 전체 아키텍처

GitHub Push
    |
    v
GitHub Actions
    |-- Maven Test
    |-- Docker Build
    |
    v
GHCR (Docker Image)
    |
    v
Docker Compose
    |
    +-- Nginx (8081)
    +-- Spring Boot (8080, internal)
    +-- MySQL (3306, internal)

## 주요 실습

### 1. Docker & Docker Compose
- Dockerfile을 활용한 이미지 빌드
- Nginx 리버스 프록시 구성
- Spring Boot와 MySQL 연결
- Docker Compose를 이용한 다중 컨테이너 관리

### 2. GitHub Actions & GHCR
- GitHub Push 시 자동 테스트
- Spring Boot Docker 이미지 자동 빌드
- GHCR에 이미지 배포
- GHCR 이미지를 이용한 Docker Compose 실행

### 3. Terraform
- Docker Provider를 이용한 컨테이너 생성
- terraform init / plan / apply / destroy 실습
- 코드 변경에 따른 컨테이너 교체 확인

### 4. Kubernetes
- kind를 이용한 로컬 Kubernetes 클러스터 구축
- Deployment 및 Pod 생성
- ReplicaSet을 통한 Pod 자동 복구
- Pod 1개에서 3개로 Scaling
- Service를 이용한 Pod 연결 및 트래픽 분산
- kubectl port-forward를 이용한 접속 테스트

## 실행 예시

### Docker Compose

`.env` 파일에 MYSQL_ROOT_PASSWORD와 MYSQL_PASSWORD를
설정한 뒤 실행합니다.

```bash
docker compose up -d
curl http://localhost:8081/messages

```

MySQL의 devopsdb 데이터베이스에는
messages(id, content) 테이블이 필요합니다.

### Terraform

```bash
cd terraform
terraform init
terraform plan
terraform apply
```

### Kubernetes

```bash
kind create cluster --name devops
kubectl apply -f k8s/
kubectl get pods
kubectl get svc
kubectl port-forward svc/nginx-svc 8084:80
```

Kubernetes 실습은 Nginx를 대상으로 하며,
Spring Boot와 MySQL은 Docker Compose에서 실행합니다.

## 학습 결과

Docker 이미지 생성부터 CI/CD, 컨테이너 배포,
인프라 코드 관리, Kubernetes 자동 복구 및
트래픽 분산까지 전체 흐름을 직접 실습했습니다.

