# http4s-stream-paged

This is a small demo project demonstrating the problems I encountered while trying to migrate a consumer for a multipart HTTP resource to http4s 1.0.0.

## Try it

Start the server

    cd 0.22/
    sbt "runMain com.l7r7.lab.http4s.multipart.server.Server"
    
This will start up a web server for testing.
    
Then run the client using http4s v0.21.22:

    cd 0.22/
    sbt "runMain com.l7r7.lab.http4s.multipart.ClientApp"
    
This will get the page, and print out the different parts. The multipart parsing works.

However, if you run the client using http4s v1.0.0-M21:

    cd 1.0/
    sbt "runMain com.l7r7.lab.http4s.multipart.ClientApp"

The program will fail with the following error:

    (run-main-0) org.http4s.MalformedMessageBodyFailure: Malformed message body: Malformed Malformed match
     org.http4s.MalformedMessageBodyFailure: Malformed message body: Malformed Malformed match
     	at org.http4s.multipart.MultipartParser$.$anonfun$ignorePrelude$2(MultipartParser.scala:209)
     	at fs2.Pull$$anon$1.cont(Pull.scala:149)
     	at fs2.Pull$BindBind$$anon$7.<init>(Pull.scala:622)
     	at fs2.Pull$BindBind.cont(Pull.scala:622)
     	at fs2.Pull$BindBind$$anon$7.cont(Pull.scala:624)
     	at fs2.Pull$.mk$1(Pull.scala:638)
     	at fs2.Pull$.fs2$Pull$$viewL(Pull.scala:643)
     	at fs2.Pull$.fs2$Pull$$go$1(Pull.scala:1118)
     	at fs2.Pull$StepRunR$1.$anonfun$done$2(Pull.scala:884)
     	at fs2.Pull$.$anonfun$compile$1(Pull.scala:805)
     	at cats.effect.IOFiber.next$2(IOFiber.scala:358)
     	at cats.effect.IOFiber.runLoop(IOFiber.scala:366)
     	at cats.effect.IOFiber.autoCedeR(IOFiber.scala:1134)
     	at cats.effect.IOFiber.run(IOFiber.scala:145)
     	at cats.effect.unsafe.WorkerThread.run(WorkerThread.scala:397)

The client code is the same (except from the new Header handling), so I'm not sure if I found a bug or if I should do it differently somehow
