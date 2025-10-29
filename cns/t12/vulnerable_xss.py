from flask import Flask, request
app = Flask(__name__)
@app.route('/')
def home():
 return '''
 <h2>Vulnerable XSS Test Page</h2>
 <form action="/search" method="GET">
 <input type="text" name="query" placeholder="Enter 
search term">
 <input type="submit" value="Search">
 </form>
 '''
@app.route('/search')
def search():
 query = request.args.get('query', '')
 # INTENTIONALLY VULNERABLE TO XSS
 response = f"""
 <html>
 <head><title>XSS Test</title></head>
 <body>
 <h2>Search Results for: {query}</h2>
 </body>
 </html>
 """
 
 return response # No sanitization! Allows XSS injection.
if __name__ == '__main__':
 app.run(debug=True)
