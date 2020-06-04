        <aside id="sidebarA">
            <nav>
                <ul>
                    <li><a class="link current" href="./index.html" >
                            Home</a></li>
                    <li >
                        <a class="link" href="#" id="catalog" onclick='showSearchandCatalog("ajaxsearch?search=")'>
                            Browse Catalog</a>
                        <ul id="subcatalog" style="display:none; margin-bottom: 0;">
                            <li onclick='showSearchResults("ajaxsearch?search=java")' style="margin-top: 0.5em;">
                                <a href="#"  id='java_quantity' ></a></li>
                            <li onclick='showSearchResults("ajaxsearch?search=PHP")'>
                                <a href="#"  id='PHP_quantity' ></a></li>
                            <li onclick='showSearchResults("ajaxsearch?search=JavaScript")' style="margin-bottom: 0em;">
                                <a href="#"  id='JavaScript_quantity'></a></li>
                        </ul>
                    </li>
                    <li><a class="link" href="#" id="email">
                            Join Email List</a></li>
                    <li><a class="link" href="#" id="service">
                            Customer Service</a></li>
                </ul>
            </nav>
        </aside>