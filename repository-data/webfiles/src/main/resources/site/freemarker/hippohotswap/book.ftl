<#include "../include/imports.ftl">
<#-- @ftlvariable name="book" type="com.bloomreach.beans.Bookdocument"-->
<#if book??>
    <p>${book.title}</p>
    <p>${book.name}</p>
    <img src="<@hst.link hippobean=book.image />"/>
    <@hst.html hippohtml=book.content/>
</#if>
