package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from Quarkus REST"

    @GET
    @Path("/test1")
    @Produces(MediaType.TEXT_PLAIN)
    fun test1() = "This is Test Resource 1"

    @GET
    @Path("/test2")
    @Produces(MediaType.TEXT_PLAIN)
    fun test2() = "This is Test Resource 2"

    @GET
    @Path("/test3")
    @Produces(MediaType.TEXT_PLAIN)
    fun test3() = "This is Test Resource 3"

}