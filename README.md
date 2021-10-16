# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`

API Endpoints:

  endpoint one: fairly fast, but often fails due to "malicious error"
  
  endpoint two: slower than endpoint one but still fairly fast. Fails more than endpoint one.
  
  endpoint three: about as fast as endpoint one, errors less than endpoint one, but often has different/incomplete users
  
  endpoint four: slow, but usually very accurate
  
  endpoint five: very fast, rarely fails, but sometimes gives incorrect return type (rentals or reviews).

Adding new command to REPL: 
1. Create a new java class that implements the interface ReplRunnable in ReplCommands/CommandRunnables.
   1. In the runCommand method, implement the desired behavior (printing should work as usual).
2. Add the command to the hashmap replCommands in Main, with the first word of the command as the key and a new instance
   of the class that implements runnable.
3. If you need items that were loaded using another command, pass the created instance of the class that contains the
   data you need and place them in a field of your class. (See similar/classify commands and classes).
4. repl should just "work": after adding entry to hashmap command should be callable.