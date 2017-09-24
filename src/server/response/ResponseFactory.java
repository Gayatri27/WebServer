package server.response;

import server.Resource;
import server.conf.Htpassword;
import server.request.Request;

public class ResponseFactory {

	/*
	 * Response codes
	 * 
	 * 200 OK 201 Created 204 No Content 304 Not Modified 400 Bad Request 401
	 * Unauthorized 403 Forbidden 404 Not Found 500 Internal Server Error
	 */

	public Response getResponse(Request request, Resource resource) {
		try {
			if (!validRequest(request))
				return new BadRequest(request);

			if (!serverAuthenticationAvailable(resource))
				return new NotFound(request);

			if (!authHeaderAvailable(request))
				return new Unauthorized(request);

			if (!userAuthenticated(request))
				return new Forbidden(request);

			if (!scriptAliased(resource))
				return handleVerbResponses(request, resource);
			else
				return processScript(request, resource);
		} catch (Exception e) {
			e.printStackTrace();
			return new InternalServerError(request);
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

	private Response handleVerbResponses(Request request, Resource resource) {
		switch (request.getVerb()) {
		case GET:
			handleGet(request, resource);
		case PUT:
			return handlePut(request, resource);
		case DELETE:
			return handleDelete(request, resource);
		case POST:
			return handlePost(request, resource);
		case HEAD:
			return new OK(request);
		default:
			return new InternalServerError(request);
		}
	}

	private Response processScript(Request request, Resource resource) {
		if (ResponseHelper.executeScript(request, resource))
			return new OK(request);
		return new InternalServerError(request);
	}

	private Response handleGet(Request request, Resource resource) {
		if (request.isModified())
			return handlePost(request, resource);
		return new NotModified(request);
	}

	private Response handlePut(Request request, Resource resource) {
		if (ResponseHelper.createFile(request, resource))
			return new Created(request);
		return new InternalServerError(request);
	}

	private Response handleDelete(Request request, Resource resource) {
		if (ResponseHelper.deleteFile(request, resource))
			return new NoContent(request);
		return new InternalServerError(request);
	}

	private Response handlePost(Request request, Resource resource) {
		if (ResponseHelper.sendFile(request, resource))
			return new OK(request);
		return new InternalServerError(request);
	}
}
