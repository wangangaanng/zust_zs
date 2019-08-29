zk.afterMount(function() {
	//Upload Progress Monitor Sample 1
	zk.UPMSample1 = zk.$extends(zk.Object, {
		updated : null,
		$init : function(uplder, filenm) {
			this._uplder = uplder;
			var id = uplder.id;
			//Add message and progressmeter
			zk.Widget.$(jq("$flist")).appendChild(
				new zul.box.Hlayout({
					id: id + "_layout",
					spacing: "6px",
					sclass: "UPMSample1",
					children: [
					    new zul.wgt.Html({
					        content: ['<div class="msg"><span>', filenm,'</span></div>'].join("")
						}),
					    new zul.wgt.Progressmeter({
						  id: id + "_pgs"
					})]
				})	
			);
		},
		update : function(sent, total) {
			zk.Widget.$(jq('$' + this._uplder.id + '_pgs')).setValue(sent);
		},
		destroy : function() {
			var layout = jq('$' + this._uplder.id + "_layout");
			if(null!=layout){
			  if (zk.ie) {
				zk.Widget.$(layout).detach();
			  } else {
				 layout.animate({ width: 1}, 500, function() {
					zk.Widget.$(layout).detach();
			  	 });
			  }
            }
		}
	});
});