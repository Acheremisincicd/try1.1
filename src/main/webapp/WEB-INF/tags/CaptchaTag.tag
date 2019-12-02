<%@ tag language="java" pageEncoding="UTF-8" %>
<div class="col-sm-12">
<div class="row">
	<div class="col-xs-4">
			<label class="pass">Enter captcha:</label>
	</div>
	    <div class="col-xs-8">
		    <img src="captcha?captchaKey=${captchaKey}" class="mb-1">
		    <input name="captcha" class="form-control" id="captcha" type="text" placeholder="Enter captcha">
			<c:if test="${not empty requestScope.registrationErrors.errorCaptcha}">
                <div id="registrationCaptchaError" class="" role="alert">
                    <p>${requestScope.registrationErrors.errorCaptcha}<p>
                </div>
            </c:if>
		</div>
<input type="hidden" name="captchaKey" value="${captchaKey}">
</div>
</div>