<%
    ui.decorateWith("appui", "standardEmrPage")

    ui.includeJavascript("uicommons", "datatables/jquery.dataTables.min.js")
    ui.includeCss("uicommons", "datatables/dataTables_jui.css")
%>

<script type="text/javascript">
    //var OPENMRS_CONTEXT_PATH = 'openmrs';
    window.sessionContext = window.sessionContext || {
        locale: "en_GB"
    };
    window.translations = window.translations || {};
</script>

<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "Usage Statistics"}
    ];
</script>

<script id="breadcrumb-template" type="text/template">
    <li>
        {{ if (!first) { }}
        <i class="icon-chevron-right link"></i>
        {{ } }}
        {{ if (!last && breadcrumb.link) { }}
        <a href="{{= breadcrumb.link }}">
        {{ } }}
        {{ if (breadcrumb.icon) { }}
        <i class="{{= breadcrumb.icon }} small"></i>
        {{ } }}
        {{ if (breadcrumb.label) { }}
        {{= breadcrumb.label }}
        {{ } }}
        {{ if (!last && breadcrumb.link) { }}
        </a>
        {{ } }}
    </li>
</script>

