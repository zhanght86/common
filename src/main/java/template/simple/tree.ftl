<#-- 写一个子集列表 -->
<#macro writeOne node, expand>
<li>
	<input type="checkbox" name="${node.nameParam}" value="${node.id}"
		<#if node.checked>
		   checked="checked"
		</#if>
			/>
	<span>${node.name}</span>
	<#if expand && (node.childList?? && node.childList?size > 0)>
		<ul>
			<@write nodeList=node.childList expand=expand/>
		</ul>
	</#if>
</li>
</#macro>

<#-- 写上级结点 -->
<#macro write nodeList expand>
	<#list nodeList as node>
		<@writeOne node=node expand=expand/>
	</#list>
</#macro>
<@write nodeList=parameters.nodeList expand=parameters.expand/>