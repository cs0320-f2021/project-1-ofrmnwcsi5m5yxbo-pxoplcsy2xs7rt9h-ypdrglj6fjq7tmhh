# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`

API Endpoints:

  endpoint one: fairly fast, but often fails due to "malicious error"
  
  endpint two: slower than endpoint one but still fairly fast. Fails more than endpoint one.
  
  endpoint three: about as fast as endpoint one, errors less than endpoint one, but often has different/incomplete users
  
  endpoint four: slow, but usually very accurate
  
  endpoint five: very fast, rarely fails, but sometimes gives incorrect return type (rentals or reviews).
