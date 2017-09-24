package server.response;

import server.Resource;
import server.conf.Htpassword;
import server.conf.MimeTypes;
import server.request.Request;

public class ResponseFactory {

	/*
	 * Response codes
	 * 
	 * 200 OK 201 Created 204 No Content 304 Not Modified 400 Bad Request 401
	 * Unauthorized 403 Forbidden 404 Not Found 500 Internal Server Error
	 */

	public Response getResponse(MimeTypes mimes, Request request, Resource resource) {
		try {
			if (!validRequest(request))
				return new BadRequest(mimes, request);

			if (!serverAuthenticationAvailable(resource))
				return new NotFound(mimes, request);

			if (!authHeaderAvailable(request))
				return new Unauthorized(mimes, request);

			if (!userAuthenticated(request))
				return new Forbidden(mimes, request);

			if (!scriptAliased(resource))
				return handleVerbResponses(mimes, request, resource);
			else
				return processScript(mimes, request, resource);
		} catch (Exception e) {
			e.printStackTrace();
			return new InternalServerError(mimes, request);
		}
	}

	private boolean validRequest(Request request) {
		return request.isValid();
	}

	private boolean serverAuthenticationAvailable(Resource resource) {
		return resource.isProtected();
	}

	private boolean authHeaderAvailable(Request request) {
		return request.isAuthHeaderAvailable();
	}

	private boolean userAuthenticated(Request request) {
		Htpassword htpassword = new Htpassword();
		// return htpassword.isAuthorized(request.getAuthInfo());
		return true;
	}

	private boolean scriptAliased(Resource resource) {
		return resource.isUriScriptAliased();
	}

	private Response handleVerbResponses(MimeTypes mimes, Request request, Resource resource) {
		switch (request.getVerb()) {
		case GET:
			handleGet(mimes, request, resource);
		case PUT:
			return handlePut(mimes, request, resource);
		case DELETE:
			return handleDelete(mimes, request, resource);
		case POST:
			return handlePost(mimes, request, resource);
		case HEAD:
			return new OK(mimes, request);
		default:
			return new InternalServerError(mimes, request);
		}
	}

	private Response processScript(MimeTypes mimes, Request request, Resource resource) {
		if (ResponseHelper.executeScript(request, resource))
			return new OK(mimes, request);
		return new InternalServerError(mimes, request);
	}

	private Response handleGet(MimeTypes mimes, Request request, Resource resource) {
		if (request.isModified())
			return handlePost(mimes, request, resource);
		return new NotModified(mimes, request);
	}

	private Response handlePut(MimeTypes mimes, Request request, Resource resource) {
		if (ResponseHelper.createFile(request, resource))
			return new Created(mimes, request);
		return new InternalServerError(mimes, request);
	}

	private Response handleDelete(MimeTypes mimes, Request request, Resource resource) {
		if (ResponseHelper.deleteFile(request, resource))
			return new NoContent(mimes, request);
		return new InternalServerError(mimes, request);
	}

	private Response handlePost(MimeTypes mimes, Request request, Resource resource) {
		if (ResponseHelper.sendFile(request, resource))
			return new OK(mimes, request);
		return new InternalServerError(mimes, request);
	}
}
