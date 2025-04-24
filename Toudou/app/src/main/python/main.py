from flask import Flask
from pymongo import MongoClient
from bson.objectid import ObjectId
from flask import request
from flask.json import jsonify
import json
import certifi

DB_URL = "mongodb+srv://thed4nm4n:XKUjXAlLKKeZ25de@cisc349.579r1.mongodb.net/?retryWrites=true&w=majority&appName=CISC349"

app = Flask(__name__)
client = MongoClient(DB_URL, tlsCAfile = certifi.where())
collection = client["CISC349"]["todo"]


@app.route("/tasks", methods=["GET", "POST", "PUT", "DELETE"])
def tasks():
    if request.method == "PUT":
        task = request.get_json()
        _id = collection.insert_one(task)
        return json.dumps({'id' : str(_id.inserted_id)})
    
    elif request.method == "POST":
        id = request.args.get("id")
        completed = not collection.find_one({"_id": ObjectId(id)})["completed"]
        collection.update_one({"_id": ObjectId(id)}, {"$set": {"completed": completed}})
        return id
    
    elif request.method == "DELETE":
        collection.delete_one({"_id": ObjectId(request.args.get("id"))})
        return request.args.get("id")

    else:
        task_list = list(collection.find({"type": request.args.get("type")}))
        return json.dumps(task_list, default=str)


if __name__ == "__main__":
    app.run(host="0.0.0.0")
