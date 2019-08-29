<?page title="ZK Theme with Bootstrap 3"?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/bootstrap/themes/bootstrap-${param.theme}.min.css" if="${not empty param.theme}"?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/bootstrap/themes/bootstrap-default.min.css" if="${empty param.theme}"?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/assets/css/docs.css" ?>
<?link rel="stylesheet" type="text/css"	href="/bootstrap/v3/assets/css/pygments-manni.css" ?>
<?script src="/bootstrap/v3/assets/js/html5shiv.js" if="${zk.ie < 9}" ?>
<?script src="/bootstrap/v3/assets/js/respond.min.js" if="${zk.ie < 9}"?>
  
<zk xmlns:w="client" xmlns:n="native">

<div class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">
  <div class="container">
  	<a href="" class="navbar-brand">ZK 7 with Bootstrap 3</a>
    <navbar zclass="none" sclass="navbar-collapse">
    	<navitem label="Getting started" onClick='Clients.showNotification("Not implemented yet")'/>
    	<navitem label="CSS" onClick='Clients.showNotification("Not implemented yet")'/>
    	<navitem label="Components" selected="true"/>
    	<navitem label="JavaScript" onClick='Clients.showNotification("Not implemented yet")'/>
    	<navitem label="Customize" onClick='Clients.showNotification("Not implemented yet")'/>
    	<nav label="Themes">
    		<navitem label="Default" onClick='Executions.sendRedirect("?theme=default")'/>
    		<navitem label="Amelia" onClick='Executions.sendRedirect("?theme=amelia")'/>
    		<navitem label="Cerulean" onClick='Executions.sendRedirect("?theme=cerulean")'/>
    		<navitem label="Cosmo" onClick='Executions.sendRedirect("?theme=cosmo")'/>
    		<!-- <navitem label="Cyborg" onClick='Executions.sendRedirect("?theme=cyborg")'/> -->    
    		<navitem label="Flatly" onClick='Executions.sendRedirect("?theme=flatly")'/>	
    		<navitem label="Journal" onClick='Executions.sendRedirect("?theme=journal")'/>	
    		<navitem label="Readable" onClick='Executions.sendRedirect("?theme=readable")'/>
    		<navitem label="Simplex" onClick='Executions.sendRedirect("?theme=simplex")'/>		
    		<navitem label="Slate" onClick='Executions.sendRedirect("?theme=slate")'/>		
    		<navitem label="Spacelab" onClick='Executions.sendRedirect("?theme=spacelab")'/>	
    		<navitem label="United" onClick='Executions.sendRedirect("?theme=united")'/>		
    	</nav>
    </navbar>
  </div>
</div>

	<div class="container bs-docs-container">
		<div class="row">
			<div class="col-md-3">
				<div class="bs-sidebar hidden-print affix">
					<include src="zk/sidebar.zul"/>
				</div>
			</div>
			<div class="col-md-9">

				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="btn-groups">Button groups</n:h1>
					</div>
					<div class="bs-callout bs-callout-info">
						<n:p>Group a series of buttons together on a single line with the button group.</n:p>
					</div>
					<include src="zk/button-groups.zul" />
				</div>
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="btn-dropdowns">Button dropdowns</n:h1>
					</div>
					<div class="bs-callout bs-callout-info">
						<n:p>Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.</n:p>
					</div>
					<include src="zk/button-dropdowns.zul" />
				</div>

				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="input-groups">Input groups</n:h1>
					</div>
					<div class="bs-callout bs-callout-info">
						<n:p><![CDATA[Extend form controls by adding text or buttons before, after, or on both sides of any text-based input. Use <code>.input-group</code> with an <code>.input-group-addon</code> to prepend or append elements to a <code>.form-control</code>.]]></n:p>
					</div>
					<include src="zk/input-groups.zul" />
				</div>
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="nav">Navs</n:h1>
					</div>
					<include src="zk/tabbox.zul" />
				</div>	
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="navbar">Navbar</n:h1>
					</div>
					<include src="zk/navbar.zul" />
				</div>	
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="pagination">Pagination</n:h1>
					</div>
					<include src="zk/paging.zul" />
				</div>	
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="labels">Labels</n:h1>
					</div>
					<include src="zk/labels.zul" />
				</div>				
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="badges">Badges</n:h1>
					</div>
					<n:p class="lead"><![CDATA[Easily highlight new or unread items by adding a <code>&lt;span class="badge"&gt;</code> to links, Bootstrap navs, and more.]]></n:p>
					<include src="zk/badges.zul" />
				</div>				
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="jumbotron">Jumbotron</n:h1>
					</div>
					<n:p><![CDATA[A lightweight, flexible component that can optional extend the entire viewport to showcase key content on your site. To make the jumbotron full width, don't include it within a <code>.container</code>. Placing it within a container will keep it at the width of the rest of your content and provide rounded corners.]]></n:p>
					<div class="bs-example">
						<div class="jumbotron">
							<div class="container">
								<n:h1>Hello, world!</n:h1>
								<n:p>
									This is a simple hero unit, a simple
									jumbotron-style component for
									calling extra attention to featured
									content or information.
								</n:p>
								<n:p>
									<a class="btn btn-primary btn-lg">
										Learn more
									</a>
								</n:p>
							</div>
						</div>
					</div>
					
					<div class="highlight">
<n:pre style="white-space:pre;"><![CDATA[<span style='color:#7f0055; '>&lt;</span><span style='color:#7f0055; '>div</span> class=<span style='color:#2a00ff; '>"</span><span style='color:#2a00ff; '>container</span><span style='color:#2a00ff; '>"</span> xmlns:n=<span style='color:#2a00ff; '>"</span><span style='color:#3f3fbf; '>native</span><span style='color:#2a00ff; '>"</span><span style='color:#7f0055; '>></span>
	<span style='color:#7f0055; '>&lt;</span>n:<span style='color:#7f0055; '>h1</span><span style='color:#7f0055; '>></span>Hello, world!<span style='color:#7f0055; '>&lt;/</span>n:<span style='color:#7f0055; '>h1</span><span style='color:#7f0055; '>></span>
	<span style='color:#7f0055; '>&lt;</span>n:<span style='color:#7f0055; '>p</span><span style='color:#7f0055; '>></span>
		This is a simple hero unit, a simple
		jumbotron-style component for
		calling extra attention to featured
		content or information.
	<span style='color:#7f0055; '>&lt;/</span>n:<span style='color:#7f0055; '>p</span><span style='color:#7f0055; '>></span>
	<span style='color:#7f0055; '>&lt;</span>n:<span style='color:#7f0055; '>p</span><span style='color:#7f0055; '>></span>
		<span style='color:#7f0055; '>&lt;</span><span style='color:#7f0055; '>a</span> class=<span style='color:#2a00ff; '>"</span><span style='color:#2a00ff; '>btn btn-primary btn-lg</span><span style='color:#2a00ff; '>"</span><span style='color:#7f0055; '>></span>
			Learn more
		<span style='color:#7f0055; '>&lt;/</span><span style='color:#7f0055; '>a</span><span style='color:#7f0055; '>></span>
	<span style='color:#7f0055; '>&lt;/</span>n:<span style='color:#7f0055; '>p</span><span style='color:#7f0055; '>></span>
<span style='color:#7f0055; '>&lt;/</span><span style='color:#7f0055; '>div</span><span style='color:#7f0055; '>></span>]]></n:pre>
					</div>
				</div>	
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="page-header">Page header</n:h1>
					</div>
					<n:p><![CDATA[A simple shell for an <code>h1</code> to appropriately space out and segment sections of content on a page. It can utilize the <code>h1</code>'s default <code>small</code> element, as well as most other components (with additional styles).]]></n:p>
					<div class="bs-example">
						<div class="page-header">
							<html>
								<![CDATA[<h1>Example page header <small>Subtext for header</small></h1>]]>
							</html>
						</div>
					</div>
					
					<div class="highlight">
<n:pre style="white-space:pre;"><![CDATA[<span style='color:#7f0055; '>&lt;</span><span style='color:#7f0055; '>div</span> class=<span style='color:#2a00ff; '>"</span><span style='color:#2a00ff; '>page-header</span><span style='color:#2a00ff; '>"</span><span style='color:#7f0055; '>></span>
	<span style='color:#7f0055; '>&lt;</span><span style='color:#7f0055; '>html</span><span style='color:#7f0055; '>></span>
		<span style='color:#3f7f59; '>&lt;![CDATA[</span>&lt;h1>Example page header &lt;small>Subtext for header&lt;/small>&lt;/h1><span style='color:#3f7f59; '>]]&gt;</span>
	<span style='color:#7f0055; '>&lt;/</span><span style='color:#7f0055; '>html</span><span style='color:#7f0055; '>></span>
<span style='color:#7f0055; '>&lt;/</span><span style='color:#7f0055; '>div</span><span style='color:#7f0055; '>></span>]]></n:pre>
					</div>
				</div>				
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="panels">Panels</n:h1>
					</div>
					<include src="zk/panel.zul" />
				</div>
				
				<div class="bs-docs-section">
					<div class="page-header">
						<n:h1 id="wells">Wells</n:h1>
					</div>
					<include src="zk/wells.zul" />
				</div>
				
			</div>
		</div>
	</div>
	
	
	<script><![CDATA[
	zk.afterMount(function () {
		$.getScript("/zk-bootstrap/bootstrap/v3/assets/js/application.js", function () {
			  if (location.hash && (!zk.ie || zk.ie > 8)) {
			  setTimeout(function () {
				$('.bs-sidebar a[href~="'+ location.hash + '"]')[0].click();
				$(document.body).scrollspy('process');
				}, 300);
			  }
		});
	});
	]]></script>
	
<script if='${execution.serverName == "www.zkoss.org" or execution.serverName == "www.potix.com"}'><![CDATA[
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));

var gafn = function () {
if (window._gat) {
	gafn = null;
	try {
		var pageTracker = _gat._getTracker("UA-121377-3");
		pageTracker._setDomainName("zkoss.org");
		pageTracker._initData();
		pageTracker._trackPageview();

	} catch (e) {
	}
} else
	setTimeout(gafn, 1000);
};
gafn();
]]></script>
</zk>