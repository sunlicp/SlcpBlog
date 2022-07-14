const btf = {
    debounce: function(t, e, n) {
        let o;
        return function() {
            const i = this
                , a = arguments
                , r = function() {
                o = null,
                n || t.apply(i, a)
            }
                , s = n && !o;
            clearTimeout(o),
                o = setTimeout(r, e),
            s && t.apply(i, a)
        }
    },
    throttle: function(t, e, n) {
        let o, i, a, r = 0;
        n || (n = {});
        const s = function() {
            r = !1 === n.leading ? 0 : (new Date).getTime(),
                o = null,
                t.apply(i, a),
            o || (i = a = null)
        };
        return function() {
            const l = (new Date).getTime();
            r || !1 !== n.leading || (r = l);
            const c = e - (l - r);
            i = this,
                a = arguments,
                c <= 0 || c > e ? (o && (clearTimeout(o),
                    o = null),
                    r = l,
                    t.apply(i, a),
                o || (i = a = null)) : o || !1 === n.trailing || (o = setTimeout(s, c))
        }
    },
    sidebarPaddingR: ()=>{
        const t = window.innerWidth
            , e = document.body.clientWidth
            , n = t - e;
        t !== e && (document.body.style.paddingRight = n + "px")
    }
    ,
    snackbarShow: (t,e=!1,n=2e3)=>{
        const {position: o, bgLight: i, bgDark: a} = GLOBAL_CONFIG.Snackbar
            , r = "light" === document.documentElement.getAttribute("data-theme") ? i : a;
        Snackbar.show({
            text: t,
            backgroundColor: r,
            showAction: e,
            duration: n,
            pos: o,
            customClass: "snackbar-css"
        })
    }
    ,
    diffDate: (t,e=!1)=>{
        const n = new Date
            , o = new Date(t)
            , i = n.getTime() - o.getTime()
            , a = 36e5
            , r = 24 * a;
        let s;
        if (e) {
            const t = i / 2592e6
                , e = i / r
                , n = i / a
                , l = i / 6e4;
            s = t > 12 ? o.toLocaleDateString().replace(/\//g, "-") : t >= 1 ? parseInt(t) + " " + GLOBAL_CONFIG.date_suffix.month : e >= 1 ? parseInt(e) + " " + GLOBAL_CONFIG.date_suffix.day : n >= 1 ? parseInt(n) + " " + GLOBAL_CONFIG.date_suffix.hour : l >= 1 ? parseInt(l) + " " + GLOBAL_CONFIG.date_suffix.min : GLOBAL_CONFIG.date_suffix.just
        } else
            s = parseInt(i / r);
        return s
    }
    ,
    loadComment: (t,e)=>{
        if ("IntersectionObserver"in window) {
            const n = new IntersectionObserver((t=>{
                    t[0].isIntersecting && (e(),
                        n.disconnect())
                }
            ),{
                threshold: [0]
            });
            n.observe(t)
        } else
            e()
    }
    ,
    scrollToDest: (t,e=500)=>{
        const n = window.pageYOffset;
        if (n > t && (t -= 70),
        "scrollBehavior"in document.documentElement.style)
            return void window.scrollTo({
                top: t,
                behavior: "smooth"
            });
        let o = null;
        t = +t,
            window.requestAnimationFrame((function i(a) {
                    o = o || a;
                    const r = a - o;
                    n < t ? window.scrollTo(0, (t - n) * r / e + n) : window.scrollTo(0, n - (n - t) * r / e),
                        r < e ? window.requestAnimationFrame(i) : window.scrollTo(0, t)
                }
            ))
    }
    ,
    animateIn: (t,e)=>{
        t.style.display = "block",
            t.style.animation = e
    }
    ,
    animateOut: (t,e)=>{
        t.addEventListener("animationend", (function e() {
                t.style.display = "",
                    t.style.animation = "",
                    t.removeEventListener("animationend", e)
            }
        )),
            t.style.animation = e
    }
    ,
    getParents: (t,e)=>{
        for (; t && t !== document; t = t.parentNode)
            if (t.matches(e))
                return t;
        return null
    }
    ,
    siblings: (t,e)=>[...t.parentNode.children].filter((n=>e ? n !== t && n.matches(e) : n !== t)),
    wrap: (t,e,n)=>{
        const o = document.createElement(e);
        for (const [t,e] of Object.entries(n))
            o.setAttribute(t, e);
        t.parentNode.insertBefore(o, t),
            o.appendChild(t)
    }
    ,
    unwrap: t=>{
        const e = t.parentNode;
        e !== document.body && (e.parentNode.insertBefore(t, e),
            e.parentNode.removeChild(e))
    }
    ,
    isHidden: t=>0 === t.offsetHeight && 0 === t.offsetWidth,
    getEleTop: t=>{
        let e = t.offsetTop
            , n = t.offsetParent;
        for (; null !== n; )
            e += n.offsetTop,
                n = n.offsetParent;
        return e
    }
    ,
    loadLightbox: t=>{
        const e = GLOBAL_CONFIG.lightbox;
        if ("mediumZoom" === e) {
            const e = mediumZoom(t);
            e.on("open", (t=>{
                    const n = "dark" === document.documentElement.getAttribute("data-theme") ? "#121212" : "#fff";
                    e.update({
                        background: n
                    })
                }
            ))
        }
        "fancybox" === e && (t.forEach((t=>{
                if ("A" !== t.parentNode.tagName) {
                    const e = t.dataset.lazySrc || t.src
                        , n = t.title || t.alt || "";
                    btf.wrap(t, "a", {
                        href: e,
                        "data-fancybox": "gallery",
                        "data-caption": n,
                        "data-thumb": e
                    })
                }
            }
        )),
        window.fancyboxRun || (Fancybox.bind("[data-fancybox]", {
            Hash: !1,
            Thumbs: {
                autoStart: !1
            }
        }),
            window.fancyboxRun = !0))
    }
    ,
    initJustifiedGallery: function(t) {
        t.forEach((function(t) {
                btf.isHidden(t) || fjGallery(t, {
                    itemSelector: ".fj-gallery-item",
                    rowHeight: 220,
                    gutter: 4,
                    onJustify: function() {
                        this.$container.style.opacity = "1"
                    }
                })
            }
        ))
    },
    updateAnchor: t=>{
        if (t !== window.location.hash) {
            t || (t = location.pathname);
            const e = GLOBAL_CONFIG_SITE.title;
            window.history.replaceState({
                url: location.href,
                title: e
            }, e, t)
        }
    }
};
