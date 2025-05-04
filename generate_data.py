import random
import datetime

# --- Configuration Data ---
first_names = [
    "John", "Jane", "Amit", "Sophia", "Carlos", "Alex", "Maria",
    "Rahul", "Priya", "Robert", "Linda", "Mohammed", "Fatima", "Emily", "David"
]
last_names = [
    "Doe", "Smith", "Kumar", "Williams", "Mendoza", "Johnson", "Brown",
    "Singh", "Patel", "Gupta", "Lee", "Garcia", "Martinez", "Davis"
]
countries = ["USA", "Canada", "India", "UK", "Mexico"]

locations = {
    "USA": {
        "states": {
            "California": ["Los Angeles", "San Francisco", "San Diego"],
            "New York": ["New York", "Buffalo", "Rochester"],
            "Texas": ["Houston", "Dallas", "Austin"],
        }
    },
    "Canada": {
        "states": {
            "Ontario": ["Toronto", "Ottawa", "Hamilton"],
            "Quebec": ["Montreal", "Quebec City"]
        }
    },
    "India": {
        "states": {
            "Telangana": ["Hyderabad", "Warangal"],
            "Maharashtra": ["Mumbai", "Pune"],
            "Karnataka": ["Bangalore", "Mysore"]
        }
    },
    "UK": {
        "states": {
            "England": ["London", "Manchester", "Birmingham"],
            "Scotland": ["Edinburgh", "Glasgow"]
        }
    },
    "Mexico": {
        "states": {
            "Jalisco": ["Guadalajara", "Puerto Vallarta"],
            "Mexico City": ["Mexico City"]
        }
    },
}
genders = ["Male", "Female"]
streets = ["Main", "Oak", "Pine", "Maple", "Cedar", "Elm", "Washington", "Lake", "Hill", "Sunset"]
account_types = ["CHK", "CCA", "HLA", "VLA", "TDA"]

def random_date_of_birth(age):
    current_year = 2025
    birth_year = current_year - age
    month = random.randint(1, 12)
    day = random.randint(1, 28)
    return f"{birth_year:04d}-{month:02d}-{day:02d}"

def random_pincode(country):
    if country in ["USA", "Canada", "UK", "Mexico"]:
        return str(random.randint(10000, 99999))
    else:
        return str(random.randint(100000, 999999))

# --- Data Generation ---
customers = []  # Each element is a list representing one customer row
accounts = []   # Each element is a list representing one account row

account_id_counter = 1001
num_customers = 1000

for cid in range(1, num_customers + 1):
    fname = random.choice(first_names)
    lname = random.choice(last_names)
    name = f"{fname} {lname}"
    gender = random.choice(genders)
    age = random.randint(18, 70)
    dob = random_date_of_birth(age)
    country = random.choice(countries)

    state = random.choice(list(locations[country]["states"].keys()))
    district = random.choice(locations[country]["states"][state])
    pincode = random_pincode(country)
    street_number = random.randint(1, 999)
    street_name = random.choice(streets)
    address = f"{street_number} {street_name} St, {district}"

    customers.append([
        cid, name, age, dob, country, state, district, pincode, address, gender
    ])

    num_accounts = random.randint(1, 3)
    for _ in range(num_accounts):
        account_number = str(random.randint(10**9, 10**10 - 1))
        acct_type = random.choice(account_types)
        if acct_type == "CHK":
            balance = round(random.uniform(100, 100000), 2)
        elif acct_type == "CCA":
            balance = round(random.uniform(100, 50000), 2)
        elif acct_type == "HLA":
            balance = round(random.uniform(50000, 500000), 2)
        elif acct_type == "VLA":
            balance = round(random.uniform(1000, 100000), 2)
        else:  # TDA
            balance = round(random.uniform(500, 50000), 2)
        accounts.append([
            account_id_counter, cid, account_number, acct_type, balance
        ])
        account_id_counter += 1

# --- Writing Data to CSV Files ---
with open("customers.csv", "w") as cust_file:
    cust_file.write("CustomerID,Name,Age,DateOfBirth,Country,State,District,Pincode,Address,Gender\n")
    for row in customers:
        cust_file.write(",".join(map(str, row)) + "\n")

with open("accounts.csv", "w") as acct_file:
    acct_file.write("AccountID,CustomerID,AccountNumber,AccountType,AccountBalance\n")
    for row in accounts:
        acct_file.write(",".join(map(str, row)) + "\n")

print("Files 'customers.csv' and 'accounts.csv' have been generated!")