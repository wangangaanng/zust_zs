<div id="sidebar" sclass="sidebar" apply="main.SidebarComposer">
	<!-- Shortcuts Button-->
	<div class="shortcuts">
		<!-- Display when expanded -->
		<div class="shortcuts-expanded">
			<button type="button" class="btn-success" iconSclass="z-icon-signal"/>
			<button type="button" class="btn-info" iconSclass="z-icon-pencil"/>
			<button type="button" class="btn-warning" iconSclass="z-icon-group"/>
			<button type="button" class="btn-danger" iconSclass="z-icon-cogs"/>
		</div>
		<!-- Display when collapsed -->
		<div class="shortcuts-collapsed">
			<button type="button" class="btn-success"/>
			<button type="button" class="btn-info"/>
			<button type="button" class="btn-warning"/>
			<button type="button" class="btn-danger"/>
		</div>
	</div>
	<!-- Navigation List -->
	<navbar id="navbar" sclass="nav-list" orient="vertical">
		<navitem label="Dashboard" iconSclass="z-icon-dashboard" selected="true"/>
		<navitem label="Typography" iconSclass="z-icon-text-width"/>
		<nav label="UI Elements" iconSclass="z-icon-desktop">
			<navitem label="Elements" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Buttone &amp; Icons" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Treeview" iconSclass="z-icon-angle-double-right"/>
			<navitem label="jQuery UI" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Nestable Lists" iconSclass="z-icon-angle-double-right"/>
			<nav label="Three Level Menu" iconSclass="z-icon-angle-double-right" sclass="grey">
				<navitem label="Item #1" iconSclass="z-icon-leaf"/>
				<nav label="4th level" iconSclass="z-icon-pencil">
					<navitem label="Add Product" iconSclass="z-icon-plus"/>
					<navitem label="View Products" iconSclass="z-icon-eye"/>
				</nav>
			</nav>
		</nav>
		<nav label="Tables" iconSclass="z-icon-list">
			<navitem label="Simple &amp; Dynamic" iconSclass="z-icon-angle-double-right"/>
			<navitem label="jqGrid plugin" iconSclass="z-icon-angle-double-right"/>
		</nav>
		<nav label="Forms" iconSclass="z-icon-edit">
			<navitem label="Form Elements" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Wizard &amp; Validation" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Wysiwyg &amp; Markdown" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Dropzone File Upload" iconSclass="z-icon-angle-double-right"/>
		</nav>
		<navitem label="Widgets" iconSclass="z-icon-list-alt" />
		<navitem id="calitem" label="Calendar" iconSclass="z-icon-calendar" sclass="notify" tooltip="calpp, position=end_center, delay=0"/>
		<navitem label="Gallery" iconSclass="z-icon-picture-o"/>
		<nav label="More Pages" iconSclass="z-icon-tag">
			<navitem label="User Profile" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Inbox" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Pricing Tables" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Invoice" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Timeline" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Login &amp; Register" iconSclass="z-icon-angle-double-right"/>
		</nav>
		<nav label="Other Pages" badgeText="5" iconSclass="z-icon-file-o">
			<navitem label="FAQ" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Error 404" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Error 500" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Grid" iconSclass="z-icon-angle-double-right"/>
			<navitem label="Blank Page" iconSclass="z-icon-angle-double-right"/>
		</nav>
	</navbar>
	
</div>