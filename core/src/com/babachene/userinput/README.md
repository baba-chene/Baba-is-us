### User Input

For handling input comming straight from the keyboard.

##### TODO:

The input processus should resend move event if the key is still being pressed.
However it must not send an event every render() call, the instanciation of
object being a cost. It should eithercreate events only once a \<time\> or
send moveStart() and moveStop() events.

It may be the role of the EventGiver to manage this event flow, why not store
the last moveStart event in order to create it again after the controller polled
the queue if no "stop" was recieved.
