provider "aws" {
  region = var.region
}

# VPC Configuration
resource "aws_vpc" "banking_api_vpc" {
  cidr_block = var.vpc_cidr
}

resource "aws_subnet" "public_subnet" {
  vpc_id                  = aws_vpc.banking_api_vpc.id
  cidr_block              = var.public_subnet_cidr
  map_public_ip_on_launch = true
}

resource "aws_subnet" "private_subnet" {
  vpc_id     = aws_vpc.banking_api_vpc.id
  cidr_block = var.private_subnet_cidr
}

# Security Group
resource "aws_security_group" "banking_api_sg" {
  vpc_id = aws_vpc.banking_api_vpc.id

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Launch Template
resource "aws_launch_template" "banking_api_lt" {
  name_prefix   = "banking-api-"
  image_id      = var.ec2_ami
  instance_type = var.ec2_instance_type
  vpc_security_group_ids = [aws_security_group.banking_api_sg.id]

  tag_specifications {
    resource_type = "instance"

    tags = {
      Name = "Banking API EC2"
    }
  }
}

# Auto Scaling Group
resource "aws_autoscaling_group" "banking_api_asg" {
  desired_capacity     = 1
  max_size             = 2
  min_size             = 1
  vpc_zone_identifier  = [aws_subnet.public_subnet.id]

  launch_template {
    id      = aws_launch_template.banking_api_lt.id
    version = "$Latest"
  }

  tag {
    key                 = "Name"
    value               = "Banking API ASG Instance"
    propagate_at_launch = true
  }
}

# RDS MySQL Database
resource "aws_db_instance" "banking_api_rds" {
  engine              = "mysql"
  instance_class      = var.db_instance_class
  allocated_storage   = 20
  username            = var.db_username
  password            = var.db_password
  publicly_accessible = true
}

# Redis Cache
resource "aws_elasticache_cluster" "banking_api_redis" {
  cluster_id      = "banking-api-cache"
  engine          = "redis"
  node_type       = var.redis_node_type
  num_cache_nodes = 1
}

# Lambda Function
resource "aws_lambda_function" "banking_api_lambda" {
  filename      = var.lambda_filename
  function_name = var.lambda_function_name
  role          = aws_iam_role.lambda_role.arn
  handler       = var.lambda_handler
  runtime       = var.lambda_runtime
}

# API Gateway
resource "aws_api_gateway_rest_api" "banking_api" {
  name        = var.api_gateway_name
  description = "REST API for Banking Service"
}

# IAM Role for Lambda
resource "aws_iam_role" "lambda_role" {
  name = "lambda_execution_role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [{
    "Effect": "Allow",
    "Principal": { "Service": "lambda.amazonaws.com" },
    "Action": "sts:AssumeRole"
  }]
}
EOF
}

# S3 Bucket
resource "aws_s3_bucket" "banking_api_bucket" {
  bucket = var.s3_bucket_name
}

# CloudWatch Logging
resource "aws_cloudwatch_log_group" "banking_api_logs" {
  name = "/aws/lambda/${var.lambda_function_name}"
}
