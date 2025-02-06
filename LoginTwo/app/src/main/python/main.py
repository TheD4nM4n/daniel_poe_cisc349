from flask import Flask, request

app = Flask(__name__)

@app.route('/login', methods=['POST'])
def root():
    request_json = request.get_json()
    username = request_json['username']
    password = request_json['password']

    return """{
    "authorized": true
    }'"""

    
if __name__ == "__main__":
    app.run(host="0.0.0.0")
 