

# Set up for mongo db

1. run docker-compose up -d for the docker-compose.yml in the resource folder
2. after creation , run this command
# Create user
db.createUser({user: "user", pwd: "password", roles:["root"]})
db.grantRolesToUser('user', [{ role: 'root', db: 'admin' }])

Use the mongodb compass to connect. Make sure the port you are using is open.