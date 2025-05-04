import mysql.connector
import pandas as pd

# MySQL Database Configuration
#DB_HOST = "bitsbankdata.cvw8s80i21wp.ap-south-1.rds.amazonaws.com"       # Change to your MySQL server
DB_HOST = "bitsbankdata.c5ukkq2q4x3z.us-east-1.rds.amazonaws.com"
DB_USER = "admin"            # Change to your MySQL username
DB_PASSWORD = "Niroop1234" # Change to your MySQL password
DB_NAME = "bitsbankdata"   # Database name

# Connect to MySQL Server
try:
    conn = mysql.connector.connect(
        host=DB_HOST,
        user=DB_USER,
        password=DB_PASSWORD
    )
    cursor = conn.cursor()

    # Create Database if it doesn't exist
    cursor.execute(f"CREATE DATABASE IF NOT EXISTS {DB_NAME};")
    print(f"Database '{DB_NAME}' is ready!")

    # Connect to the database
    conn.database = DB_NAME

    # Create Customers Table
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS customers (
            CustomerID INT PRIMARY KEY,
            Name VARCHAR(100),
            Age INT,
            DateOfBirth DATE,
            Country VARCHAR(50),
            State VARCHAR(50),
            District VARCHAR(50),
            Pincode VARCHAR(20),
            Address TEXT,
            Gender VARCHAR(10)
        );
    """)

    # Create Accounts Table
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS accounts (
            AccountID INT PRIMARY KEY,
            CustomerID INT,
            AccountNumber VARCHAR(20),
            AccountType VARCHAR(10),
            AccountBalance DECIMAL(10,2),
            FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
        );
    """)

    print("Tables created successfully!")
    # Load Customers and Accounts Data

    print("Database connection closed.")
except mysql.connector.Error as err:
    print(f"Error: {err}")

# Function to Load CSV Data into MySQL Table
def load_csv_to_mysql(file_path, table_name):
    try:
        df = pd.read_csv(file_path)  # Load CSV file
        for _, row in df.iterrows():
            values = tuple(row)
            placeholders = ", ".join(["%s"] * len(row))  # Dynamic placeholders
            query = f"INSERT INTO {table_name} VALUES ({placeholders})"
            cursor.execute(query, values)
        conn.commit()
        print(f"Data successfully inserted into {table_name}")
    except Exception as e:
        print(f"Error loading data into {table_name}: {e}")

    load_csv_to_mysql(r"C:\Users\91900\OneDrive\Desktop\BitsPilani\CloudComputing\customers.csv", "customers")
    load_csv_to_mysql(r"C:\Users\91900\OneDrive\Desktop\BitsPilani\CloudComputing\accounts.csv", "accounts")

    # Close Connection
    cursor.close()
    conn.close()