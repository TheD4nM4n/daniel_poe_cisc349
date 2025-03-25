from flask import Flask
from pymongo import MongoClient
from flask import request
from flask.json import jsonify
import json
import certifi

DB_URL = "mongodb+srv://thed4nm4n:XKUjXAlLKKeZ25de@cisc349.579r1.mongodb.net/?retryWrites=true&w=majority&appName=CISC349"

app = Flask(__name__)
client = MongoClient(DB_URL, tlsCAfile = certifi.where())
db = client["CISC349"]

# Welcome route
@app.route('/')
def index():
    return "<h1>Get out of our server!!</h1>"

# Add user
@app.route('/add', methods=['POST'])
def add():
    collection = db["customers"]
    request_data = request.get_json()
    name = request_data['name']
    address = request_data['address']
    phone = request_data['phone']
    data = { "name": name, "address": address, "phone": phone }
    _id = collection.insert_one(data)
    return json.dumps({'id' : str(_id.inserted_id)})

@app.route('/all', methods=['POST'])
def all():
    collection = db["customers"]
    customers = list(collection.find())
    # we need to convert _id to str.
    return json.dumps(customers, default=str)

#Update user
@app.route('/update', methods=['POST'])
def update():
    collection = db["customers"]
    request_data = request.get_json()
    id = request_data['_id']
    name = request_data['name']
    address = request_data['address']
    phone = request_data['phone']
    comments = request_data['comments']
    print(f'ID: {id}, Name: {name}, Address: {address}, Phone: {phone}')
    filter = {'_id' : id}
    newvalues = {"$push": {'comments': comments}}

    _id = collection.update_one(filter, newvalues)
    return json.dumps({'id' : id})


if __name__ == '__main__':
    app.run(host="0.0.0.0", port="5000")