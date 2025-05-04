variable "region" {
  description = "AWS region to deploy resources in"
  type        = string
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
}

variable "public_subnet_cidr" {
  description = "CIDR block for the public subnet"
  type        = string
}

variable "private_subnet_cidr" {
  description = "CIDR block for the private subnet"
  type        = string
}

variable "ec2_ami" {
  description = "AMI ID for the EC2 instance"
  type        = string
}

variable "ec2_instance_type" {
  description = "Instance type for EC2"
  type        = string
}

variable "db_instance_class" {
  description = "Instance class for RDS"
  type        = string
}

variable "db_username" {
  description = "Username for RDS database"
  type        = string
}

variable "db_password" {
  description = "Password for RDS database"
  type        = string
  sensitive   = true
}

variable "redis_node_type" {
  description = "Redis cache node type"
  type        = string
}

variable "lambda_function_name" {
  description = "Name of the Lambda function"
  type        = string
}

variable "lambda_filename" {
  description = "Path to the Lambda deployment package (ZIP)"
  type        = string
}

variable "lambda_handler" {
  description = "Handler function for Lambda"
  type        = string
}

variable "lambda_runtime" {
  description = "Runtime for Lambda"
  type        = string
}

variable "api_gateway_name" {
  description = "Name of the API Gateway"
  type        = string
}

variable "s3_bucket_name" {
  description = "Name of the S3 bucket"
  type        = string
}
