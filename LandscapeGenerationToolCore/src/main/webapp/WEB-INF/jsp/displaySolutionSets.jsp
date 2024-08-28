<!DOCTYPE html>

<!-- 
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 -->

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Landscape Generation Tool - Solution Set Manager</title>

<link type="text/css" rel="stylesheet" 	href="ext/bootstrap/css/bootstrap.css">
<link type="text/css" rel="stylesheet" 	href="<c:url value="/static/webresources/css/tor_ui.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/main.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/login.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/notifications.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/products.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/sidebarnavigation.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/solutionsets.css"/>">
<link type="text/css" rel="stylesheet"	href="<c:url value="/static/webresources/css/summarysection.css"/>">

<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- todo why do i need this 1.2.7 version ??? -->
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="ext/bootstrap/js/bootstrap.js"></script>
</head>
<body>

	<!-- Notifications box -->
	<article class="e-window e-squared">
		<div>
			<header class="e-head">
				<h1 class="e-title">Notifications</h1>
			</header>
			<div class="e-content" id="notifications"></div>
		</div>
	</article>

	<div id="applicationBar">
	    <div class="e-applicationBar">
	  		<button class="e-app"><span>Landscape Generator Tool</span></button>
		  	<div id="buttonsBGColor">
		  		<div id="applicationBarButtonsDiv">
		  			<button class="e-app no-logo"><span>Help</span></button>
		  			<button class="e-app no-logo disableCursorButton"><span id="notificationCount">Notification</span></button>
		  			<!-- Spring Security Taglibs, check whether the user is Authenticated -->
		  			<sec:authorize access="! isAuthenticated()">
	    				<!-- not logged in, display login button -->
	    				<button class="e-app no-logo" id="login"><span>Login</span></button>	    				
	    				<c:if test="${not empty param.login_error}">
	    					<!-- if this is a redirect with login_error -->
							<font color="red">Unsuccessful login attempt.
								<br/>Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
							</font>
						</c:if>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<!-- logged in, display logout button -->
						<form method="post" action='<c:url value="logout"/>'>
							<button class="e-app no-logo" id="logout" type="submit">
								<span>Logout</span>
							</button>
						</form>
					</sec:authorize>
				</div>
				<div id="userName">
					<!-- Spring Security Taglibs, display Username, only when the user is authenticated -->
					<sec:authorize access="isAuthenticated()">
						<button class="e-app disableCursorButton" id="loggedInUserIcon">
							<span><sec:authentication property="principal.username" /></span>
						</button>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>

	<!-- login components -->
	<div id=LoginArea>
		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>
			<span class="e-input"><input placeholder="Username..."
				type="text" name='j_username'></span> <span class="e-input"><input
				placeholder="Password..." type="password" name='j_password'></span>
			<span class="e-icon"><input class="e-btn" name="submit"
				type="submit" value="submit" /></span>
		</form>
	</div>
	<div id="wizard_tabs">
		 <div id="left-tabs-column">
			<h1 id="new-landscape-def-heading">
				<span>New Landscape Definition</span>
			</h1>
			<!-- the 4 tabs -->

			 <ul class="tabs" id="tab-navigation">
				<li><a href="#SelectProductsPane" class="">Select Products</a></li>
				<li><a href="#SelectIndividualSolutionSetsPane" class="">Select Individual Solution Sets</a>
					<p class="paragraphs">Add additional Solution Sets to your definition</p></li>
				<li><a href="#SetNetworkParametersPane" class="">Set Network Parameters</a>
					<p class="paragraphs">Add Network Parameters to your definition</p></li>
				<li class="disabled"><a>Generate Landscape Definition File</a></li>
			</ul>
		</div>

		<!-- the 4 panes assoicated with the tabs -->
		<div class="panes">
			<ul class="e-breadcrumbs e-loose" id="tab-navigation2">
				<li><a href="#SelectProductsPane" class="" title="Add products to your definition. They will show in the Definition Summary.">Select Products</a></li>
				<li><a href="#SelectIndividualSolutionSetsPane" class="" title="Add Solution Sets to your definition. They willl show in the Definition Summary.">Select
						Individual Solution Sets</a></li>
				<li><a href="#SetNetworkParametersPane" class="" title="&nbsp;">Set
						Network Parameters</a></li>			
			</ul>

            <p id="bread-crumb-tab-description"></p>

			<div id="SelectProductsPane">
				<div id="Products-for-selection">
					<hr class="black-line" />
					<div id="searchdiv">
						<label for="kwd_search"> <span class="e-input e-search"
							id="search"> <input type="text" class="kwd_search"
								placeholder="Search for a Product" value="" /></span>
						</label>
					</div>
					<div class="scrollsectionProducts">
						<!-- Products table with checkboxes is appended here..  -->
						<div id="productstable"></div>
					</div>
					<hr class="black-line" />
					<p>
						<button class="e-btn next" id="nextButton">
							<span>Next</span>
						</button>
					</p>
				</div>
				<!-- end for Products-for-selection -->
			</div>

			<div id="SelectIndividualSolutionSetsPane">
				<div id="solution-sets-for-selection">
					<hr class="black-line" />
					<div id="searchdiv">
						<label for="kwd_search"> <span class="e-input e-search"
							id="search"> <input type="text" class="kwd_search"
								placeholder="Search for a Solution Set" value="" /></span></label>
					</div>
					<div class="scrollsection1">
						<!-- Solution Set table with checkboxes is appended here..  -->
						<div id="solutionsettable"></div>
					</div>
					<hr class="black-line" />
					<p>
						<button class="e-btn next"><span>Next</span></button>
						<button class="e-btn prev float-left"><span>Back</span></button>
					</p>
				</div>
				<!-- end solution-sets-for-selection -->
			</div>
			<!-- end of SelectIndividualSolutionSets  -->

			<div id="SetNetworkParametersPane">
				<h3>Set Network Parameters</h3>
				<hr class="black-line"/>
				<p>
					<button class="e-btn next" disabled><span>Next</span></button>
					<button class="e-btn prev float-left"><span>Back</span></button>					
				</p>
			</div>

			<div id="GenerateLandscapeDefinitionFilePane">
				<h3>Generate Landscape Definition File</h3>
				<i>...button to finally generate xml goes here</i>
				<p>
					<button class="e-btn prev"><span>Back</span></button>
				</p>
			</div>

		</div>
		<!-- end of panes -->
		<div id="definition-summary">
			<div id="top-of-definition-summary">
				<h3>Definition Summary</h3>
				<button class="e-btn">
					<span>Save</span>
				</button>
			</div>
			<hr class="black-line" />
			<h4>Products</h4>
			<!-- <hr class="grey-line"/> -->
			<!-- Products table with checkboxes is appended here..  -->
			<div class="products-summary-table"></div>
			<hr class="grey-line" />
			<h4>Additional Solution Sets</h4>
			<hr class="grey-line" />
			<div class="scrollsection2"></div>
			<hr class="grey-line" />
			<h4>Network Parameters</h4>
			<hr class="black-line" />
			<div id="bottom-of-definition-summary">
				<button class="e-btn" disabled="disabled">
					<span>Generate File</span>
				</button>
			</div>
		</div>
		<!-- end of definition-summary -->
	</div>
	<!-- end of tabs -->

	<script src="<c:url value="/static/webresources/js/main.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/navigation.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/quicksearch.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/solutionsets.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/products.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/definitionsummary.js"/>"></script>
	<script src="<c:url value="/static/webresources/js/notification.js"/>"></script>
</body>
</html>