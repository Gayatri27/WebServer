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
				return new BadRequest(resource);

			if (!serverAuthenticationAvailable(resource))
				return new NotFound(resource);

			if (!authHeaderAvailable(request))
				return new Unauthorized(resource);

			if (!userAuthenticated(request))
				return new Forbidden(resource);

			if (!scriptAliased(resource))
				return handleVerbResponses(request, resource);
			else
				return processScript(request, resource);
		} catch (Exception e) {
			e.printStackTrace();
			return new InternalServerError(resource);
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
			return new OK(resource);
		case PUT:
			return new OK(resource);
		case DELETE:
			return new OK(resource);
		case POST:
			return new OK(resource);
		case HEAD:
			return new OK(resource);
		default:
			return new InternalServerError(resource);
		}
	}

	private Response processScript(Request request, Resource resource) {
		if(executeScript(request))
			return new OK(resource);
		return new InternalServerError(resource);
	}
	
	private boolean executeScript(Request request) {
		return false;
	}
}
