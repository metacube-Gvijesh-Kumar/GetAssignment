const express = require('express');
const path = require('path');
const app = express()
const port = 3000

app.use(express.static("public"));

app.get('/', function(req, res) {
  res.sendFile(path.join(__dirname, '/public/assignment.html'));
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})