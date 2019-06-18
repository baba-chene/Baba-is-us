# Client/Server functions

## Client

`Client(int eventBufferLength, int updateBufferLength)`
Creates the buffers with indicated sizes, and start a new thread.

`void setHost(String IPAdress, int port)`
Sets the IP adress and the port to connect to.

`void connect(String IPAdress, int port)`
Sets the IP adress and the port to connect to, and starts connecting to it.

`void connect()`
Connects to the last IP adress and port set.

`void disconnect()`
Disconnects from the current server.

`void shutdown()`
Terminates the current thread.

`void addEvent(Event event)`
Adds the event to the event buffer.

`boolean isUpdateBufferEmpty()`
Indicates if the update buffer is empty or not.

`Update getUpdate()`
Returns and removes the least recent update in the buffer.


## Server

`Server(int eventBufferLength, int updateBufferLength)`
Creates the buffers with indicated sizes, and start a new thread.

`void open(int port)`
Sets the port to listen on, and starts listening on it.

`void open()`
Starts listening on the last port set.

`void close()`
Stops listening.

`void disconnect()`
Disconnects from the current client, and returns to listening on the last port set.

`void shutdown()`
Terminates the current thread.

`void addUpdate(Update update)`
Adds the event to the update buffer.

`boolean isEventBufferEmpty()`
Indicates if the event buffer is empty or not.

`Event getEvent()`
Returns and removes the least recent event in the buffer.
