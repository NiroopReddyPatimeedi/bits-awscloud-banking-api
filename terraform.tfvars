region               = "us-east-1"
vpc_cidr             = "10.0.0.0/16"
public_subnet_cidr   = "10.0.1.0/24"
private_subnet_cidr  = "10.0.2.0/24"

ec2_ami              = "ami-12345678"
ec2_instance_type    = "t2.micro"

db_instance_class    = "db.t3.micro"
db_username          = "admin"
db_password          = "password"

redis_node_type      = "cache.t4g.micro"

lambda_function_name = "banking_api_handler"
lambda_filename      = "function.zip"
lambda_handler       = "index.handler"
lambda_runtime       = "nodejs18.x"

api_gateway_name     = "BankingAPI"
s3_bucket_name       = "banking-api-assets"
