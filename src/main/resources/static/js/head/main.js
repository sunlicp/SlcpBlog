document.addEventListener("DOMContentLoaded", (function() {
        let t = !1;
        const e = ()=>{
            btf.sidebarPaddingR(),
                document.body.style.overflow = "hidden",
                // btf.animateIn(document.getElementById("menu-mask"), "to_show 0.5s"),
                document.getElementById("sidebar-menus").classList.add("open"),
                t = !0
        }
            , n = ()=>{
            const e = document.body;
            e.style.overflow = "",
                e.style.paddingRight = "",
                // btf.animateOut(document.getElementById("menu-mask"), "to_hide 0.5s"),
                document.getElementById("sidebar-menus").classList.remove("open"),
                t = !1
        }
            , o = function() {
            const t = GLOBAL_CONFIG.highlight;
            if (!t)
                return;
            const e = t.highlightCopy
                , n = t.highlightLang
                , o = GLOBAL_CONFIG_SITE.isHighlightShrink
                , i = t.highlightHeightLimit
                , c = e || n || void 0 !== o
                , s = "highlighjs" === t.plugin ? document.querySelectorAll("figure.highlight") : document.querySelectorAll('pre[class*="language-"]');
            if (!c && !i || !s.length)
                return;
            const a = "prismjs" === t.plugin;
            let l = ""
                , d = "";
            const r = !0 === o ? "closed" : "";
            void 0 !== o && (l = `<i class="fas fa-angle-down expand ${r}"></i>`),
            e && (d = '<div class="copy-notice"></div><i class="fas fa-paste copy-button"></i>');
            const u = t=>{
                const e = t.parentNode;
                e.classList.add("copy-true");
                const n = window.getSelection()
                    , o = document.createRange();
                a ? o.selectNodeContents(e.querySelectorAll("pre code")[0]) : o.selectNodeContents(e.querySelectorAll("table .code pre")[0]),
                    n.removeAllRanges(),
                    n.addRange(o);
                n.toString();
                ((t,e)=>{
                        if (document.queryCommandSupported && document.queryCommandSupported("copy"))
                            if (document.execCommand("copy"),
                            void 0 !== GLOBAL_CONFIG.Snackbar)
                                btf.snackbarShow(GLOBAL_CONFIG.copy.success);
                            else {
                                const t = e.previousElementSibling;
                                t.innerText = GLOBAL_CONFIG.copy.success,
                                    t.style.opacity = 1,
                                    setTimeout((()=>{
                                            t.style.opacity = 0
                                        }
                                    ), 700)
                            }
                        else
                            void 0 !== GLOBAL_CONFIG.Snackbar ? btf.snackbarShow(GLOBAL_CONFIG.copy.noSupport) : e.previousElementSibling.innerText = GLOBAL_CONFIG.copy.noSupport
                    }
                )(0, t.lastChild),
                    n.removeAllRanges(),
                    e.classList.remove("copy-true")
            }
                , m = function(t) {
                const e = t.target.classList;
                e.contains("expand") ? (t=>{
                        const e = [...t.parentNode.children].slice(1);
                        t.firstChild.classList.toggle("closed"),
                            btf.isHidden(e[e.length - 1]) ? e.forEach((t=>{
                                    t.style.display = "block"
                                }
                            )) : e.forEach((t=>{
                                    t.style.display = "none"
                                }
                            ))
                    }
                )(this) : e.contains("copy-button") && u(this)
            }
                , f = function() {
                this.classList.toggle("expand-done")
            };
            function g(t, e, n) {
                const o = document.createDocumentFragment();
                if (c) {
                    const e = document.createElement("div");
                    e.className = `highlight-tools ${r}`,
                        e.innerHTML = l + t + d,
                        e.addEventListener("click", m),
                        o.appendChild(e)
                }
                if (i && e.offsetHeight > i + 30) {
                    const t = document.createElement("div");
                    t.className = "code-expand-btn",
                        t.innerHTML = '<i class="fas fa-angle-double-down"></i>',
                        t.addEventListener("click", f),
                        o.appendChild(t)
                }
                "hl" === n ? e.insertBefore(o, e.firstChild) : e.parentNode.insertBefore(o, e)
            }
            n ? a ? s.forEach((function(t) {
                    const e = `<div class="code-lang">${t.getAttribute("data-language") ? t.getAttribute("data-language") : "Code"}</div>`;
                    btf.wrap(t, "figure", {
                        class: "highlight"
                    }),
                        g(e, t)
                }
            )) : s.forEach((function(t) {
                    let e = t.getAttribute("class").split(" ")[1];
                    "plain" !== e && void 0 !== e || (e = "Code");
                    g(`<div class="code-lang">${e}</div>`, t, "hl")
                }
            )) : a ? s.forEach((function(t) {
                    btf.wrap(t, "figure", {
                        class: "highlight"
                    }),
                        g("", t)
                }
            )) : s.forEach((function(t) {
                    g("", t, "hl")
                }
            ))
        };
        const i = function() {
                const t = document.getElementById("rightside")
                    , e = window.innerHeight + 56;
                if (document.body.scrollHeight <= e)
                    return void (t.style.cssText = "opacity: 1; transform: translateX(-58px)");
                let n = 0
                    , o = !0;
                const i = document.getElementById("page-header")
                    , c = "function" == typeof chatBtnHide
                    , s = "function" == typeof chatBtnShow;
                window.scrollCollect = ()=>btf.throttle((function(a) {
                        const l = window.scrollY || document.documentElement.scrollTop
                            , d = function(t) {
                            const e = t > n;
                            return n = t,
                                e
                        }(l);
                        l > 56 ? (d ? (i.classList.contains("nav-visible") && i.classList.remove("nav-visible"),
                        s && !0 === o && (chatBtnHide(),
                            o = !1)) : (i.classList.contains("nav-visible") || i.classList.add("nav-visible"),
                        c && !1 === o && (chatBtnShow(),
                            o = !0)),
                            i.classList.add("nav-fixed"),
                        "0" === window.getComputedStyle(t).getPropertyValue("opacity") && (t.style.cssText = "opacity: 0.8; transform: translateX(-58px)")) : (0 === l && i.classList.remove("nav-fixed", "nav-visible"),
                            t.style.cssText = "opacity: ''; transform: ''"),
                        document.body.scrollHeight <= e && (t.style.cssText = "opacity: 0.8; transform: translateX(-58px)")
                    }
                ), 200)(),
                    window.addEventListener("scroll", scrollCollect)
            }
            , c = function() {
                const t = GLOBAL_CONFIG_SITE.isToc
                    , e = GLOBAL_CONFIG.isAnchor
                    , n = document.getElementById("article-container");
                if (!n || !t && !e)
                    return;
                let o, i, c, s, a;
                if (t) {
                    const t = document.getElementById("card-toc");
                    i = t.getElementsByClassName("toc-content")[0],
                        o = i.querySelectorAll(".toc-link");
                    const e = t.querySelector(".toc-percentage");
                    a = i.classList.contains("is-expand"),
                        c = t=>{
                            const o = n.clientHeight
                                , i = document.documentElement.clientHeight
                                , c = (t - n.offsetTop) / (o > i ? o - i : document.documentElement.scrollHeight - i)
                                , s = Math.round(100 * c)
                                , a = s > 100 ? 100 : s <= 0 ? 0 : s;
                            e.textContent = a
                        }
                        ,
                        window.mobileToc = {
                            open: ()=>{
                                t.style.cssText = "animation: toc-open .3s; opacity: 1; right: 55px"
                            }
                            ,
                            close: ()=>{
                                t.style.animation = "toc-close .2s",
                                    setTimeout((()=>{
                                            t.style.cssText = "opacity:''; animation: ''; right: ''"
                                        }
                                    ), 100)
                            }
                        },
                        i.addEventListener("click", (t=>{
                                t.preventDefault();
                                const e = t.target.classList;
                                if (e.contains("toc-content"))
                                    return;
                                const n = e.contains("toc-link") ? t.target : t.target.parentElement;
                                btf.scrollToDest(btf.getEleTop(document.getElementById(decodeURI(n.getAttribute("href")).replace("#", ""))), 300),
                                window.innerWidth < 900 && window.mobileToc.close()
                            }
                        )),
                        s = t=>{
                            const e = t.getBoundingClientRect().top
                                , n = i.scrollTop;
                            e > document.documentElement.clientHeight - 100 && (i.scrollTop = n + 150),
                            e < 100 && (i.scrollTop = n - 150)
                        }
                }
                const l = n.querySelectorAll("h1,h2,h3,h4,h5,h6");
                let d = "";
                window.tocScrollFn = function() {
                    return btf.throttle((function() {
                            const n = window.scrollY || document.documentElement.scrollTop;
                            t && c(n),
                                function(n) {
                                    if (0 === n)
                                        return !1;
                                    let c = ""
                                        , r = "";
                                    if (l.forEach((function(t, e) {
                                            if (n > btf.getEleTop(t) - 80) {
                                                const n = t.id;
                                                c = n ? "#" + encodeURI(n) : "",
                                                    r = e
                                            }
                                        }
                                    )),
                                    d !== r && (e && btf.updateAnchor(c),
                                        d = r,
                                        t)) {
                                        if (i.querySelectorAll(".active").forEach((t=>{
                                                t.classList.remove("active")
                                            }
                                        )),
                                        "" === c)
                                            return;
                                        const t = o[r];
                                        if (t.classList.add("active"),
                                            setTimeout((()=>{
                                                    s(t)
                                                }
                                            ), 0),
                                            a)
                                            return;
                                        let e = t.parentNode;
                                        for (; !e.matches(".toc"); e = e.parentNode)
                                            e.matches("li") && e.classList.add("active")
                                    }
                                }(n)
                        }
                    ), 100)()
                }
                    ,
                    window.addEventListener("scroll", tocScrollFn)
            }
            , s = ()=>{
                const t = document.body;
                t.classList.add("read-mode");
                const e = document.createElement("button");
                e.type = "button",
                    e.className = "fas fa-sign-out-alt exit-readmode",
                    t.appendChild(e),
                    e.addEventListener("click", (function n() {
                            t.classList.remove("read-mode"),
                                e.remove(),
                                e.removeEventListener("click", n)
                        }
                    ))
            }
            , a = ()=>{
                "light" === ("dark" === document.documentElement.getAttribute("data-theme") ? "dark" : "light") ? (activateDarkMode(),
                    saveToLocal.set("theme", "dark", 2),
                void 0 !== GLOBAL_CONFIG.Snackbar && btf.snackbarShow(GLOBAL_CONFIG.Snackbar.day_to_night)) : (activateLightMode(),
                    saveToLocal.set("theme", "light", 2),
                void 0 !== GLOBAL_CONFIG.Snackbar && btf.snackbarShow(GLOBAL_CONFIG.Snackbar.night_to_day)),
                "function" == typeof utterancesTheme && utterancesTheme(),
                "function" == typeof changeGiscusTheme && changeGiscusTheme(),
                "object" == typeof FB && window.loadFBComment(),
                window.DISQUS && document.getElementById("disqus_thread").children.length && setTimeout((()=>window.disqusReset()), 200),
                "function" == typeof runMermaid && window.runMermaid()
            }
            , l = t=>{
                const e = document.getElementById("rightside-config-hide").classList;
                e.toggle("show"),
                t.classList.contains("show") && (e.add("status"),
                    setTimeout((()=>{
                            e.remove("status")
                        }
                    ), 300)),
                    t.classList.toggle("show")
            }
            , d = ()=>{
                btf.scrollToDest(0, 500)
            }
            , r = ()=>{
                const t = document.documentElement.classList;
                t.contains("hide-aside") ? saveToLocal.set("aside-status", "show", 2) : saveToLocal.set("aside-status", "hide", 2),
                    t.toggle("hide-aside")
            }
            , u = ()=>{
                "0" === window.getComputedStyle(document.getElementById("card-toc")).getPropertyValue("opacity") ? window.mobileToc.open() : window.mobileToc.close()
            }
        ;
        document.getElementById("rightside").addEventListener("click", (function(t) {
                const e = t.target.id ? t.target : t.target.parentNode;
                switch (e.id) {
                    case "go-up":
                        d();
                        break;
                    case "rightside_config":
                        l(e);
                        break;
                    case "mobile-toc-button":
                        u();
                        break;
                    case "readmode":
                        s();
                        break;
                    case "darkmode":
                        a();
                        break;
                    case "hide-aside-btn":
                        r()
                }
            }
        ));
        const m = ()=>{
            document.querySelectorAll("#sidebar-menus .site-page.group").forEach((function(t) {
                    t.addEventListener("click", (function() {
                            this.classList.toggle("hide")
                        }
                    ))
                }
            ))
        }
            , f = function() {
            document.querySelectorAll("#article-container .tab > button").forEach((function(t) {
                    t.addEventListener("click", (function(t) {
                            const e = this
                                , n = e.parentNode;
                            if (!n.classList.contains("active")) {
                                const t = n.parentNode.nextElementSibling
                                    , o = btf.siblings(n, ".active")[0];
                                o && o.classList.remove("active"),
                                    n.classList.add("active");
                                const i = e.getAttribute("data-href").replace("#", "");
                                [...t.children].forEach((t=>{
                                        t.id === i ? t.classList.add("active") : t.classList.remove("active")
                                    }
                                ));
                                const c = t.querySelectorAll(`#${i} .fj-gallery`);
                                c.length > 0 && btf.initJustifiedGallery(c)
                            }
                        }
                    ))
                }
            ))
        }
            , g = ()=>{
            document.querySelectorAll("#article-container .tabs .tab-to-top").forEach((function(t) {
                    t.addEventListener("click", (function() {
                            btf.scrollToDest(btf.getEleTop(btf.getParents(this, ".tabs")), 300)
                        }
                    ))
                }
            ))
        }
            , h = function(t) {
            t.forEach((t=>{
                    const e = t
                        , n = e.getAttribute("datetime");
                    e.innerText = btf.diffDate(n, !0),
                        e.style.display = "inline"
                }
            ))
        };
        window.refreshFn = function() {
            GLOBAL_CONFIG_SITE.isPost ? (void 0 !== GLOBAL_CONFIG.noticeOutdate && function() {
                const t = GLOBAL_CONFIG.noticeOutdate
                    , e = btf.diffDate(GLOBAL_CONFIG_SITE.postUpdate);
                if (e >= t.limitDay) {
                    const n = document.createElement("div");
                    n.className = "post-outdate-notice",
                        n.textContent = t.messagePrev + " " + e + " " + t.messageNext;
                    const o = document.getElementById("article-container");
                    "top" === t.position ? o.insertBefore(n, o.firstChild) : o.appendChild(n)
                }
            }(),
            GLOBAL_CONFIG.relativeDate.post && h(document.querySelectorAll("#post-meta time"))) : (GLOBAL_CONFIG.relativeDate.homepage && h(document.querySelectorAll("#recent-posts time")),
            GLOBAL_CONFIG.runtime && (()=>{
                    const t = document.getElementById("runtimeshow");
                    if (t) {
                        const e = t.getAttribute("data-publishDate");
                        t.innerText = btf.diffDate(e) + " " + GLOBAL_CONFIG.runtime
                    }
                }
            )(),
                (()=>{
                        const t = document.getElementById("last-push-date");
                        if (t) {
                            const e = t.getAttribute("data-lastPushDate");
                            t.innerText = btf.diffDate(e, !0)
                        }
                    }
                )(),
                function() {
                    const t = document.querySelectorAll("#aside-cat-list .card-category-list-item.parent i");
                    t.length && t.forEach((function(t) {
                            t.addEventListener("click", (function(t) {
                                    t.preventDefault(),
                                        this.classList.toggle("expand");
                                    const e = this.parentNode.nextElementSibling;
                                    btf.isHidden(e) ? e.style.display = "block" : e.style.display = "none"
                                }
                            ))
                        }
                    ))
                }()),
                c(),
            GLOBAL_CONFIG_SITE.isHome && (()=>{
                    const t = document.getElementById("scroll-down");
                    t && t.addEventListener("click", (function() {
                            btf.scrollToDest(document.getElementById("content-inner").offsetTop, 300)
                        }
                    ))
                }
            )(),
                o(),
            GLOBAL_CONFIG.isPhotoFigcaption && document.querySelectorAll("#article-container img").forEach((function(t) {
                    const e = t.parentNode
                        , n = t.title || t.alt;
                    if (n && !e.parentNode.classList.contains("justified-gallery")) {
                        const o = document.createElement("div");
                        o.className = "img-alt is-center",
                            o.textContent = n,
                            e.insertBefore(o, t.nextSibling)
                    }
                }
            )),
                i();
            const t = document.querySelectorAll("#article-container .fj-gallery");
            t.length && function(t) {
                if (t.forEach((t=>{
                        t.querySelectorAll("img").forEach((t=>{
                                const e = t.getAttribute("data-lazy-src");
                                e && (t.src = e),
                                    btf.wrap(t, "div", {
                                        class: "fj-gallery-item"
                                    })
                            }
                        ))
                    }
                )),
                    window.fjGallery)
                    return void setTimeout((()=>{
                            btf.initJustifiedGallery(t)
                        }
                    ), 100);
                const e = document.createElement("link");
                e.rel = "stylesheet",
                    e.href = GLOBAL_CONFIG.source.justifiedGallery.css,
                    document.body.appendChild(e),
                    getScript(`${GLOBAL_CONFIG.source.justifiedGallery.js}`).then((()=>{
                            btf.initJustifiedGallery(t)
                        }
                    ))
            }(t),
                btf.loadLightbox(document.querySelectorAll("#article-container img:not(.no-lightbox)")),
                (()=>{
                        const t = document.querySelectorAll("#article-container :not(.highlight) > table, #article-container > table");
                        t.length && t.forEach((t=>{
                                btf.wrap(t, "div", {
                                    class: "table-wrap"
                                })
                            }
                        ))
                    }
                )(),
                function() {
                    const t = document.querySelectorAll("#article-container .hide-button");
                    t.length && t.forEach((function(t) {
                            t.addEventListener("click", (function(t) {
                                    this.classList.add("open");
                                    const e = this.nextElementSibling.querySelectorAll(".fj-gallery");
                                    e.length && btf.initJustifiedGallery(e)
                                }
                            ))
                        }
                    ))
                }(),
                f(),
                g(),
                function() {
                    let t = !1;
                    const e = document.querySelector("#comment-switch > .switch-btn");
                    e && e.addEventListener("click", (function() {
                            this.classList.toggle("move"),
                                document.querySelectorAll("#post-comment > .comment-wrap > div").forEach((function(t) {
                                        btf.isHidden(t) ? t.style.cssText = "display: block;animation: tabshow .5s" : t.style.cssText = "display: none;animation: ''"
                                    }
                                )),
                            t || "function" != typeof loadOtherComment || (t = !0,
                                loadOtherComment())
                        }
                    ))
                }(),
                document.getElementById("toggle-menu").addEventListener("click", (()=>{
                        e()
                    }
                ))
        }
            ,
            refreshFn(),
            window.addEventListener("resize", (()=>{
                    btf.isHidden(document.getElementById("toggle-menu")) && t && n()
                }
            )),
            // document.getElementById("menu-mask").addEventListener("click", (t=>{
            //         n()
            //     }
            // )),
            m(),
        GLOBAL_CONFIG.islazyload && (window.lazyLoadInstance = new LazyLoad({
            elements_selector: "img",
            threshold: 0,
            data_src: "lazy-src"
        })),
        void 0 !== GLOBAL_CONFIG.copyright && (()=>{
                const t = GLOBAL_CONFIG.copyright;
                document.body.oncopy = e=>{
                    let n;
                    e.preventDefault();
                    const o = window.getSelection(0).toString();
                    return n = o.length > t.limitCount ? o + "\n\n\n" + t.languages.author + "\n" + t.languages.link + window.location.href + "\n" + t.languages.source + "\n" + t.languages.info : o,
                        e.clipboardData ? e.clipboardData.setData("text", n) : window.clipboardData.setData("text", n)
                }
            }
        )()
    }
));
