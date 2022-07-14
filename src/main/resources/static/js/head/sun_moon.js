function switchNightMode() {
    document.querySelector('body').insertAdjacentHTML('beforeend', '<div class="Cuteen_DarkSky"><div class="Cuteen_DarkPlanet"></div></div>'),
        setTimeout(function() {
            document.querySelector('body').classList.contains('DarkMode') ? (document.querySelector('body').classList.remove('DarkMode'), localStorage.setItem('isDark', '0'), document.getElementById('modeicon').setAttribute('xlink:href', '#icon-moon'),document.getElementById('changeMode-top').setAttribute('xlink:href', '#icon-moon'),$("#messageImg").removeClass("sunImg").addClass("darkImg")) : (document.querySelector('body').classList.add('DarkMode'), localStorage.setItem('isDark', '1'), document.getElementById('modeicon').setAttribute('xlink:href', '#icon-sun'),document.getElementById('changeMode-top').setAttribute('xlink:href', '#icon-sun'),$("#messageImg").removeClass("darkImg").addClass("sunImg")),
                setTimeout(function() {
                    document.getElementsByClassName('Cuteen_DarkSky')[0].style.transition = 'opacity 3s';
                    document.getElementsByClassName('Cuteen_DarkSky')[0].style.opacity = '0';
                    setTimeout(function() {
                        document.getElementsByClassName('Cuteen_DarkSky')[0].remove();
                    }, 1e3);
                }, 2e3)
        })
    const nowMode = document.documentElement.getAttribute('data-theme') === 'dark' ? 'dark' : 'light'
    if (nowMode === 'light') {
        activateDarkMode()
        saveToLocal.set('theme', 'dark', 2)
        GLOBAL_CONFIG.Snackbar !== undefined && btf.snackbarShow(GLOBAL_CONFIG.Snackbar.day_to_night)
        document.getElementById('modeicon').setAttribute('xlink:href', '#icon-sun')
        document.getElementById('changeMode-top').setAttribute('xlink:href', '#icon-sun')
        $("#messageImg").removeClass("darkImg").addClass("sunImg")

    } else {
        activateLightMode()
        saveToLocal.set('theme', 'light', 2)
        document.querySelector('body').classList.add('DarkMode'), document.getElementById('modeicon').setAttribute('xlink:href', '#icon-moon')
        document.getElementById('changeMode-top').setAttribute('xlink:href', '#icon-moon')
        $("#messageImg").removeClass("sunImg").addClass("darkImg")
    }
    // handle some cases
    typeof utterancesTheme === 'function' && utterancesTheme()
    typeof FB === 'object' && window.loadFBComment()
    window.DISQUS && document.getElementById('disqus_thread').children.length && setTimeout(() => window.disqusReset(), 200)
}


document.addEventListener('scroll',function(){

    //滚动条高度+视窗高度 = 可见区域底部高度
    var visibleBottom = window.scrollY + document.documentElement.clientHeight;
    //可见区域顶部高度
    var visibleTop = window.scrollY;
    var pagination = document.getElementById('pagination');
    var eventlistner = document.getElementsByClassName('post-copyright')[0];
    if (eventlistner&&pagination){
        var centerY = eventlistner.offsetTop+(eventlistner.offsetHeight/2);
        if(centerY>visibleTop&&centerY<visibleBottom){
            pagination.style.display = 'flex';
        }else{
            pagination.style.display = 'none';
        }
    }
})
