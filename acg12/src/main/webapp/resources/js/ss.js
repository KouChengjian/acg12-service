!function (t) {
    function e(n) {
        if (i[n])return i[n].exports;
        var a = i[n] = {exports: {}, id: n, loaded: !1};
        return t[n].call(a.exports, a, a.exports, e), a.loaded = !0, a.exports
    }

    var i = {};
    return e.m = t, e.c = i, e.p = "", e(0)
}({
    0: function (t, exports, e) {
        var i = e(1);
        e(4), e(54), e(12);
        var n, a = e(55), r = new LazyImage;
        window.hashParams = {};
        var s = {
            Configure: {
                url: "/web_api/season/index_global",
                shrinkPid: {3: 3, 6: 10},
                filterSort: ["v", "area", "stat", "y", "q", "tag"],
                filterClass: {
                    v: ".right.tab_nav",
                    area: ".right.tab_area",
                    stat: ".right.tab_status",
                    y: ".right.tab_year",
                    q: ".right.tab_update",
                    tag: ".right.tab_style",
                    t: ".v_sort.tab_sort"
                },
                pageSize: 20,
                defaultHashParams: {p: 1, v: "0", area: "", stat: "0", y: "0", q: "0", tag: "", t: "1", sort: 0},
                hashIsDefault: !0
            }, initConfigure: function () {
                167 == window.tid && (this.Configure.url = "/web_api/season/index_cn", this.Configure.filterSort.splice(1, 1), this.Configure.filterSort.splice(3, 1), this.Configure.defaultHashParams = {
                    p: 1,
                    v: "0",
                    stat: "0",
                    y: "0",
                    tag: "",
                    t: "1",
                    sort: 0
                }), window.hashParams = $.extend(!0, {}, this.Configure.defaultHashParams)
            }, formatNum: function (t) {
                return t = "string" == typeof t ? parseInt(t) : t, t ? t <= 9999 ? t + "浜鸿拷鐣�" : (t / 1e4).toFixed(1) + "涓囦汉杩界暘" : "0浜鸿拷鐣�"
            }, fmtUpdateT: function (t, e) {
                var i = function (t, e) {
                    return t.getFullYear() === e.getFullYear() && t.getMonth() === e.getMonth() && t.getDate() === e.getDate()
                };
                if (t = "string" == typeof t ? parseInt(t) : t) {
                    var n, a = new Date, r = new Date(1e3 * t),
                        s = new Date(a.getFullYear(), a.getMonth(), a.getDate()),
                        o = new Date(a.getFullYear(), a.getMonth(), a.getDate() - 1),
                        l = new Date(r.getFullYear(), r.getMonth(), r.getDate()),
                        d = new Date(a.getFullYear(), "1", "1"), c = new Date(r.getFullYear(), "1", "1"),
                        u = r.getHours() < 10 ? "0" + r.getHours() : r.getHours(),
                        h = r.getMinutes() < 10 ? "0" + r.getMinutes() : r.getMinutes();
                    return n = i(l, o) ? "鏄ㄥぉ" + u + ":" + h : i(l, s) ? "浠婂ぉ" + u + ":" + h : i(d, c) ? r.getMonth() + 1 + "鏈�" + r.getDate() + "鏃�" : r.getFullYear() + "骞�" + (r.getMonth() + 1) + "鏈�", n + "鏇存柊"
                }
                return "--鏈�--鏃ユ洿鏂�"
            }, fmtPubT: function (t, e) {
                if (t = "string" == typeof t ? parseInt(t) : t, e)return e + "寮€鎾�";
                if (t) {
                    var i, n = new Date, a = new Date(1e3 * t);
                    return i = n.getFullYear() == a.getFullYear() ? a.getMonth() + 1 + "鏈�" + a.getDate() + "鏃�" : a.getFullYear() + "骞�" + (a.getMonth() + 1) + "鏈�", i + "寮€鎾�"
                }
                return "--鏈�--鏃ュ紑鎾�"
            }, setHash: function (t) {
                if (t)for (var e in t)window.hashParams[e] = t[e];
                var i = window.hashParams, n = [];
                for (var e in i)n.push(e + "=" + i[e]);
                this.pageList(), window.location.href = "#" + n.join("&")
            }, parseHash: function () {
                if (window.location.hash) {
                    var t = window.location.hash.substr(1), e = t.split("&");
                    for (var i in e)if (e.hasOwnProperty(i)) {
                        var n = e[i].split("=");
                        n[1] && (window.hashParams[n[0]] = n[1])
                    }
                    this.setFilterStatus()
                }
            }, setFilterStatus: function () {
                for (var t in window.hashParams)if ("sort" === t) {
                    var e = $(".v_sort.tab_sort a.on");
                    1 == window.hashParams[t] ? e.removeClass("desc").addClass("asc") : e.removeClass("asc").addClass("desc")
                } else $(this.Configure.filterClass[t] + ' a[data-value="' + window.hashParams[t] + '"]').addClass("on").siblings().removeClass("on")
            }, resizeCkc: function () {
                $(".special_selector a.ckc").each(function (t, e) {
                    var i = $.trim(e.innerHTML).replace(/[\u4E00-\u9FA5]/g, "**").length;
                    i > 6 && $(e).css({width: "78px"})
                });
                for (var t in this.Configure.shrinkPid) {
                    var e = $('div[data-pid="' + t + '"]');
                    e.find("a.ckc").length <= 4 * this.Configure.shrinkPid[t] && e.find(".height-trigger").remove()
                }
            }, formatTitle: function (t) {
                t.each(function (t, e) {
                    $(e).height() > 44 && $(e).parent().append('<div class="after">...</div>')
                })
            }, pageList: function () {
                var t = {
                    page: window.hashParams.p,
                    page_size: this.Configure.pageSize,
                    version: window.hashParams.v,
                    is_finish: window.hashParams.stat,
                    start_year: window.hashParams.y,
                    tag_id: window.hashParams.tag,
                    index_type: window.hashParams.t,
                    index_sort: window.hashParams.sort
                }, e = this;
                window.hashParams.area && (t.area = window.hashParams.area), window.hashParams.q && (t.quarter = window.hashParams.q), n && n.abort && n.abort(), n = $.ajax({
                    url: this.Configure.url,
                    data: t,
                    type: "get",
                    dataType: "json",
                    beforeSend: function () {
                        $(".v_ul").hide(), $(".pagelistbox").hide()
                    }
                }).done(function (t) {
                    return t && t.result ? void e.render(t) : ($(".v_ul").html('<li class="no-data"><span>鑾峰彇鏁版嵁澶辫触锛岃鍒锋柊鍚庨噸璇曪紒</span></li>'), $(".pagelistbox").empty(), window.index_void_show && window.index_void_show(), !1)
                }).fail(function () {
                    $(".v_ul").html('<li class="no-data"><span>鑾峰彇鏁版嵁澶辫触锛岃鍒锋柊鍚庨噸璇曪紒</span></li>'), $(".pagelistbox").empty(), window.index_void_show && window.index_void_show()
                }).always(function () {
                    $(".v_ul").fadeIn(50), $(".pagelistbox").show(), a.dotdotdot("ul.v_ul .info_wrp .t"), r.lazy(".v_ul")
                })
            }, render: function (t) {
                var e = this, n = t.result, a = $(".pagelistbox"), r = $("ul.v_ul");
                if (!n.list.length) {
                    var s = '<div class="no-data-filter"><span>娌℃湁杩欐牱鐨勭暘鍓у摝锝�</span><a href="javascript:;" class="reset-btn">閲嶇疆绛涢€�</a></div>';
                    return 167 == window.tid && (s = '<div class="no-data-filter"><span>娌℃湁杩欐牱鐨勫浗浜у姩鐢诲摝锝�</span><a href="javascript:;" class="reset-btn">閲嶇疆绛涢€�</a></div>'), r.add(".tab_pagelist").empty(), r.append(s), window.index_void_show && window.index_void_show(), !1
                }
                $.each(n.list, function (t, e) {
                    e.cover = window.bfsImage(e.cover, 160, 214)
                }), r.empty().append(i({
                    data: n.list,
                    fn: {formatNum: e.formatNum, fmtUpdateT: e.fmtUpdateT, fmtPubT: e.fmtPubT},
                    sortType: window.hashParams.t
                })), a.empty(), 0 != n.count && (pagelist(a, parseInt(window.hashParams.p), n.pages, n.count, function (t) {
                    e.setHash({p: t})
                }, "", 2, !0), a.one("click", "a", function () {
                    $("html body").scrollTop($(".bangumi-list").offset().top)
                }))
            }, init: function () {
                this.initConfigure(), this.resizeCkc(), this.parseHash(), this.pageList(), this.afterRender()
            }, resizeMinH: function () {
                setTimeout(function () {
                    $(".v_container").css({minHeight: $(".bgm-index-nav").outerHeight()})
                }, 300)
            }, afterRender: function () {
                var t = this;
                $(".tab_sort").on("click", "a", function (e) {
                    e.preventDefault();
                    var i = $(e.currentTarget), n = 0, a = i.attr("data-value");
                    i.hasClass("on") ? i.hasClass("desc") ? (i.removeClass("desc").addClass("asc"), n = 1) : i.removeClass("asc").addClass("desc") : (i.siblings().removeClass("on"), i.removeClass("asc").addClass("on desc")), t.setHash({
                        t: a,
                        sort: n,
                        p: 1
                    })
                }), $(".special_selector").on("click", ".right a", function (e) {
                    e.preventDefault();
                    var i = $(e.currentTarget), n = i.closest(".special_mbox");
                    if (!i.hasClass("on")) {
                        i.siblings().removeClass("on"), i.addClass("on");
                        var a = {};
                        a[t.Configure.filterSort[n.index()]] = i.attr("data-value") || "", a.p = 1, t.setHash(a)
                    }
                    t.resizeMinH()
                }), $(document).on("click", ".reset-btn", function (e) {
                    e.preventDefault(), t.setHash(t.Configure.defaultHashParams), t.setFilterStatus()
                }), $(".height-trigger").on("click", function (e) {
                    var i = $(e.currentTarget), n = i.closest(".right"),
                        a = 30 * t.Configure.shrinkPid[n.attr("data-pid")], r = n[0].scrollHeight + 30;
                    i.hasClass("expand") ? (n.css({
                        maxHeight: r,
                        height: r
                    }), i.html("鏀惰捣"), i.removeClass("expand").addClass("shrink")) : (n.css({
                        maxHeight: a,
                        height: a
                    }), i.html("灞曞紑"), i.removeClass("shrink").addClass("expand")), t.resizeMinH()
                })
            }
        };
        window.Test = s, $(function () {
            s.init()
        })
    }, 1: function (t, exports, e) {
        var i = e(2);
        t.exports = function (t) {
            var e, n = [], a = t || {};
            return function (t, a, r, s, o, l, d) {
                (function () {
                    var d = a;
                    if ("number" == typeof d.length)for (var c = 0, u = d.length; c < u; c++) {
                        var h = d[c];
                        n.push("<li" + i.attr("data-season_id", "" + h.season_id, !0, !0) + '><div class="preview"><div class="cover"><a' + i.attr("href", "" + s(h.url), !0, !0) + ' target="_blank"' + i.attr("title", "" + h.title, !0, !0) + "><img" + i.attr("data-img", "" + s(h.cover), !0, !0) + i.attr("alt", "" + h.title, !0, !0) + '><div class="shadow"><span class="sort-info">'), "0" == l ? n.push("" + i.escape(null == (e = r.fmtUpdateT(h.update_time)) ? "" : e)) : "1" == l ? n.push("" + i.escape(null == (e = r.formatNum(h.favorites)) ? "" : e)) : "2" == l && n.push("" + i.escape(null == (e = r.fmtPubT(h.pub_time, h.pub_string)) ? "" : e)), n.push("</span></div>"), 6 !== h.season_status && 8 !== h.season_status || n.push('<div class="cover_tag_payview">' + i.escape(null == (e = h.badge) ? "" : e) + "</div>"), 7 !== h.season_status && 9 !== h.season_status || n.push('<div class="cover_tag_payfast">' + i.escape(null == (e = h.badge) ? "" : e) + "</div>"), n.push('</a></div><div class="info_wrp"><div class="info"><a' + i.attr("href", "" + s(h.url), !0, !0) + ' target="_blank"' + i.attr("title", "" + h.title, !0, !0) + '><div class="t">' + i.escape(null == (e = h.title) ? "" : e) + '</div></a><p class="num">'), 2 == h.is_finish ? n.push("鍏�" + i.escape(null == (e = h.total_count) ? "" : e) + "璇�") : h.newest_ep_index == -1 ? n.push("鍗冲皢寮€鎾�") : o(t(h.newest_ep_index)) ? n.push("鏇存柊鑷�" + i.escape(null == (e = h.newest_ep_index) ? "" : e)) : n.push("鏇存柊鑷崇" + i.escape(null == (e = h.newest_ep_index) ? "" : e) + "璇�"), n.push("</p></div></div></div></li>")
                    } else {
                        var u = 0;
                        for (var c in d) {
                            u++;
                            var h = d[c];
                            n.push("<li" + i.attr("data-season_id", "" + h.season_id, !0, !0) + '><div class="preview"><div class="cover"><a' + i.attr("href", "" + s(h.url), !0, !0) + ' target="_blank"' + i.attr("title", "" + h.title, !0, !0) + "><img" + i.attr("data-img", "" + s(h.cover), !0, !0) + i.attr("alt", "" + h.title, !0, !0) + '><div class="shadow"><span class="sort-info">'), "0" == l ? n.push("" + i.escape(null == (e = r.fmtUpdateT(h.update_time)) ? "" : e)) : "1" == l ? n.push("" + i.escape(null == (e = r.formatNum(h.favorites)) ? "" : e)) : "2" == l && n.push("" + i.escape(null == (e = r.fmtPubT(h.pub_time, h.pub_string)) ? "" : e)), n.push("</span></div>"), 6 !== h.season_status && 8 !== h.season_status || n.push('<div class="cover_tag_payview">' + i.escape(null == (e = h.badge) ? "" : e) + "</div>"), 7 !== h.season_status && 9 !== h.season_status || n.push('<div class="cover_tag_payfast">' + i.escape(null == (e = h.badge) ? "" : e) + "</div>"), n.push('</a></div><div class="info_wrp"><div class="info"><a' + i.attr("href", "" + s(h.url), !0, !0) + ' target="_blank"' + i.attr("title", "" + h.title, !0, !0) + '><div class="t">' + i.escape(null == (e = h.title) ? "" : e) + '</div></a><p class="num">'), 2 == h.is_finish ? n.push("鍏�" + i.escape(null == (e = h.total_count) ? "" : e) + "璇�") : h.newest_ep_index == -1 ? n.push("鍗冲皢寮€鎾�") : o(t(h.newest_ep_index)) ? n.push("鏇存柊鑷�" + i.escape(null == (e = h.newest_ep_index) ? "" : e)) : n.push("鏇存柊鑷崇" + i.escape(null == (e = h.newest_ep_index) ? "" : e) + "璇�"), n.push("</p></div></div></div></li>")
                        }
                    }
                }).call(this)
            }.call(this, "Number" in a ? a.Number : "undefined" != typeof Number ? Number : void 0, "data" in a ? a.data : "undefined" != typeof data ? data : void 0, "fn" in a ? a.fn : "undefined" != typeof fn ? fn : void 0, "httpsChk" in a ? a.httpsChk : "undefined" != typeof httpsChk ? httpsChk : void 0, "isNaN" in a ? a.isNaN : "undefined" != typeof isNaN ? isNaN : void 0, "sortType" in a ? a.sortType : "undefined" != typeof sortType ? sortType : void 0, "undefined" in a ? a.undefined : void 0), n.join("")
        }
    }, 2: function (t, exports, e) {
        "use strict";
        function i(t) {
            return null != t && "" !== t
        }

        function n(t) {
            return (Array.isArray(t) ? t.map(n) : t && "object" == typeof t ? Object.keys(t).filter(function (e) {
                return t[e]
            }) : [t]).filter(i).join(" ")
        }

        function a(t) {
            return s[t] || t
        }

        function r(t) {
            var e = String(t).replace(o, a);
            return e === "" + t ? t : e
        }

        exports.merge = function l(t, e) {
            if (1 === arguments.length) {
                for (var n = t[0], a = 1; a < t.length; a++)n = l(n, t[a]);
                return n
            }
            var r = t["class"], s = e["class"];
            (r || s) && (r = r || [], s = s || [], Array.isArray(r) || (r = [r]), Array.isArray(s) || (s = [s]), t["class"] = r.concat(s).filter(i));
            for (var o in e)"class" != o && (t[o] = e[o]);
            return t
        }, exports.joinClasses = n, exports.cls = function (t, e) {
            for (var i = [], a = 0; a < t.length; a++)e && e[a] ? i.push(exports.escape(n([t[a]]))) : i.push(n(t[a]));
            var r = n(i);
            return r.length ? ' class="' + r + '"' : ""
        }, exports.style = function (t) {
            return t && "object" == typeof t ? Object.keys(t).map(function (e) {
                return e + ":" + t[e]
            }).join(";") : t
        }, exports.attr = function (t, e, i, n) {
            return "style" === t && (e = exports.style(e)), "boolean" == typeof e || null == e ? e ? " " + (n ? t : t + '="' + t + '"') : "" : 0 == t.indexOf("data") && "string" != typeof e ? (JSON.stringify(e).indexOf("&") !== -1 && console.warn("Since Jade 2.0.0, ampersands (`&`) in data attributes will be escaped to `&amp;`"), e && "function" == typeof e.toISOString && console.warn("Jade will eliminate the double quotes around dates in ISO form after 2.0.0"), " " + t + "='" + JSON.stringify(e).replace(/'/g, "&apos;") + "'") : i ? (e && "function" == typeof e.toISOString && console.warn("Jade will stringify dates in ISO form after 2.0.0"), " " + t + '="' + exports.escape(e) + '"') : (e && "function" == typeof e.toISOString && console.warn("Jade will stringify dates in ISO form after 2.0.0"), " " + t + '="' + e + '"')
        }, exports.attrs = function (t, e) {
            var i = [], a = Object.keys(t);
            if (a.length)for (var r = 0; r < a.length; ++r) {
                var s = a[r], o = t[s];
                "class" == s ? (o = n(o)) && i.push(" " + s + '="' + o + '"') : i.push(exports.attr(s, o, !1, e))
            }
            return i.join("")
        };
        var s = {"&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;"}, o = /[&<>"]/g;
        exports.escape = r, exports.rethrow = function d(t, i, n, a) {
            if (!(t instanceof Error))throw t;
            if (!("undefined" == typeof window && i || a))throw t.message += " on line " + n, t;
            try {
                a = a || e(3).readFileSync(i, "utf8")
            } catch (r) {
                d(t, null, n)
            }
            var s = 3, o = a.split("\n"), l = Math.max(n - s, 0), c = Math.min(o.length, n + s),
                s = o.slice(l, c).map(function (t, e) {
                    var i = e + l + 1;
                    return (i == n ? "  > " : "    ") + i + "| " + t
                }).join("\n");
            throw t.path = i, t.message = (i || "Jade") + ":" + n + "\n" + s + "\n\n" + t.message, t
        }, exports.DebugItem = function (t, e) {
            this.lineno = t, this.filename = e
        }
    }, 3: function (t, exports) {
    }, 4: function (t, exports) {
        var e = {
            version: function () {
                var t = navigator.userAgent;
                navigator.appVersion;
                return {
                    trident: /Trident/i.test(t),
                    presto: /Presto/i.test(t),
                    webKit: /AppleWebKit/i.test(t),
                    gecko: /Gecko/i.test(t) && !/KHTML/i.test(t),
                    mobile: /AppleWebKit.*Mobile.*/i.test(t),
                    ios: /\(i[^;]+;( U;)? CPU.+Mac OS X/i.test(t),
                    android: /Android/i.test(t) || /Linux/i.test(t),
                    windowsphone: /Windows Phone/i.test(t),
                    iPhone: /iPhone/i.test(t),
                    iPad: /iPad/i.test(t),
                    MicroMessenger: /MicroMessenger/i.test(t),
                    webApp: !/Safari/i.test(t),
                    edge: /edge/i.test(t)
                }
            }(), language: (navigator.browserLanguage || navigator.language).toLowerCase()
        };
        window.SliderController = function (t) {
            this.params = $.extend(!0, {mode: "click"}, t), this._mouseIn = !1
        }, window.SliderController.prototype = {
            pointer: 0, length: 0, timer: null, init: function () {
                var t = this, e = this.params;
                $(e.parent).find(".p-loading").length > 0 ? this.loading = $(e.parent).find(".p-loading") : this.loading = $('<div class="p-loading"></div>'), this.wrapper = $(e.wrapper).appendTo($(e.parent)), this.container = this.wrapper.find("ul").eq(0), this.bar = $(e.bar), e.barContainer ? this.bar.appendTo(this.wrapper.find(e.barContainer)) : this.bar.appendTo(this.wrapper), e.dataSrc && (0 == $(e.parent).find(".p-loading").length && this.loading.appendTo(this.wrapper), "string" == typeof e.dataSrc ? $.getJSON(e.dataSrc, function (e) {
                    t.loading.remove(), t.load(e)
                }) : "object" == typeof e.dataSrc && (this.loading.remove(), this.load(e.dataSrc)))
            }, load: function (t) {
                var e, i = this.params;
                if (e = i.onLoad ? i.onLoad(t) : t.list ? t.list : t.result)for (var n in e)"object" == typeof e[n] && (i.onData && i.onData(e[n]) !== !1 || !i.onData) && this.add(e[n]);
                if (0 == this.length) {
                    $('<li class="no-data">娌℃湁鏁版嵁</li>').appendTo(this.container);
                    this.bar.hide()
                } else 1 == this.length && this.bar.hide();
                $("[bar]:eq(0)", this.bar).addClass("on"), this.setTimer(), i.initCallback && i.initCallback(this.wrapper, t)
            }, add: function (t, e, i) {
                var n = this.params, a = 100 * this.length, r = $(n.barItem);
                r.appendTo(this.bar).attr("bar", "bar");
                var s = this.length;
                0 == this.length && r.addClass("on"), n.barRenderCallback && n.barRenderCallback(r, t, s, i);
                var o;
                "function" == typeof n.item ? o = n.item(t, s) : (o = $(n.item), $("img", o).attr("src", httpsChk(t.img)), 1 === s && i ? $("a", o).attr({
                    "data-target-url": httpsChk(t.link) || "",
                    href: i || httpsChk(t.link) || ""
                }) : $("a", o).attr("href", httpsChk(t.link))), o.attr("preview", "preview"), o.appendTo(this.container), n.renderCallback && n.renderCallback(this.wrapper, t, s, o, i), this.bindBarEvent(r), this.bindPreviewEvent(o), a += 100, this.length++, this.container.width(a + "%"), "undefined" != typeof e && e(this.wrapper, t)
            }, bind: function () {
                var t = this.params;
                this.wrapper = $(t.wrapper), this.container = this.wrapper.find("ul").eq(0), this.bar = null;
                var e = this.container.children();
                this.length = e.length, this.container.width(100 * this.length + "%"), this.bindPreviewEvent(e), this.setTimer(), t.initCallback && t.initCallback(this.wrapper)
            }, bindBarEvent: function (t) {
                var i = this;
                void 0 !== e && (e.version.mobile || e.version.ios || e.version.android || e.version.windowsphone) || t.hover(function () {
                    i._mouseIn = !0, clearInterval(i.timer), "hover" == i.params.mode && i.slide($(this).index())
                }, function () {
                    i._mouseIn = !1, i.setTimer()
                }), t.click(function () {
                    i.slide($(this).index())
                })
            }, barHandler: function (t) {
                var e = this.bar.find("[bar]").eq(t);
                e.hasClass("on") || (this.bar.find("[bar]").removeClass("on"), e.addClass("on"))
            }, bindPreviewEvent: function (t) {
                var e = this;
                t.hover(function () {
                    clearInterval(e.timer)
                }, function () {
                    e.setTimer()
                })
            }, slide: function (t) {
                var e = this, i = this.params;
                this._mouseIn || e.setTimer(), this.container.stop(!0, !0).animate({"margin-left": 100 * -t + "%"}, 200), this.barHandler(t), this.pointer != t && i.slideCallback && i.slideCallback(this.wrapper, t), this.pointer = t
            }, setTimer: function () {
                var t = this, e = this.params;
                clearInterval(this.timer), 0 != e.timeout && 1 != this.length && (this.timer = setInterval(function () {
                    t.next()
                }, e.timeout || 5e3))
            }, next: function () {
                this.slide(this.pointer < this.length - 1 ? this.pointer + 1 : 0)
            }, prev: function () {
                this.slide(this.pointer > 0 ? this.pointer - 1 : this.length - 1)
            }, destroy: function () {
                clearInterval(this.timer), this.wrapper.remove()
            }
        }, window.renderBangumiPromoteSlider = function (t, e, i) {
            var n = i ? i : "";
            if (t) {
                var a = t.list ? t.list : t.result;
                if (!a.length)return;
                var r = {
                    mode: "hover",
                    parent: e ? e : ".bangumi-pmt-slider",
                    wrapper: $('<div class="mini-preview-wrapper"><div class="mini-preview-list-wrapper"><ul class="mini-preview"></ul></div><div class="s-bottom"><div class="info"></div></div></div>'),
                    renderCallback: function (t, e, i) {
                        var n = $('<div class="info-item"><a class="t" href="' + e.link + '" title="' + e.title + '" target="_blank">' + e.title + "</a></div>").appendTo($(".info", t));
                        $("[preview] img", t).eq(i).attr("alt", e.title), 0 == i && n.show()
                    },
                    slideCallback: function (t, e) {
                        $(".info .info-item", t).stop().hide(), $(".info", t).find(".info-item:eq(" + e + ")").stop(!0, !0).fadeIn(300)
                    },
                    item: function (t, e) {
                        return $('<li><a target="_blank" href="' + t.link + '"><img src="' + httpsChk(t.img) + '" alt="' + t.title + '"></a></li>')
                    },
                    bar: $('<ul class="slider-bar ' + n + '"></ul>'),
                    barContainer: ".s-bottom",
                    barItem: "<li><a></a></li>",
                    dataSrc: {list: a}
                }, s = new SliderController(r);
                s.init()
            }
        }, window.httpsChk = function (t) {
            var e = window.location.href.toLowerCase().indexOf("https") > -1;
            return e && t.toLowerCase().indexOf("https") < 0 ? t.replace("http", "https") : t
        };
        var i = "lazy-src";
        window.LazyLoad = function (t) {
            var e = t.slice(0), n = e.length, a = function () {
                for (var t = $(window).scrollTop(), r = t + 2 * screen.height, s = 0, o = e.length; s < o; s++) {
                    var l = $(e[s]);
                    !l.attr("src") && l.attr(i) && l.offset().top != t && r > l.offset().top && (l.attr("src", httpsChk(l.attr(i))), n--)
                }
                n <= 0 && $(window).off("touchmove touchend scroll", a)
            };
            $(window).on("touchmove touchend scroll", a), a()
        }, window.bfsImage = function (t, e, i) {
            if (!t)return t;
            t = httpsChk(t);
            var n = /i[0-2]/;
            if (n.test(t)) {
                var a, r = "";
                if (t.indexOf("?") >= 0 && (a = t.split("?"), t = a[0], r = "?" + a[1]), t.indexOf("/bfs/") >= 0 || t.indexOf("/group1/") >= 0) {
                    var n = /_+[0-9]+x[0-9]/;
                    if (n.test(t))return t;
                    var s = t.split("."), o = s[s.length - 1];
                    t += "_" + e + "x" + i + "." + o
                } else {
                    var n = /\d+_\d+\//;
                    n.test(t) && (t = t.replace(n, "")), t = t.replace("com/", "com/" + e + "_" + i + "/")
                }
                t += r
            }
            return t
        }
    }, 5: function (t, exports, e) {
        function i(t, e) {
            if (window.reportObserver) {
                if (e = e || {}, window.reportMsgObj) {
                    t = t || {};
                    for (var n in t)window.reportMsgObj[n + "_" + t[n]] = e
                }
            } else setTimeout(function () {
                i(t, e)
            }, 1e3)
        }

        function n(t) {
            return "/" === t[0] ? "/" === t[1] ? location.protocol + t : location.origin + t : t.length > 4 && "http" === t.substr(0, 4) ? t : location.href + t
        }

        var a = e(6), r = e(7), s = a, o = s.identify();
        o && r.set("bsource", o, "d7"), s.changeURLform(), t.exports.report = i, t.exports.parseUrl = n
    }, 6: function (t, exports, e) {
        var i = e(7), n = e(8);
        t.exports = {
            getURLlist: function (t) {
                var e = new RegExp("(^|&)" + t + "=([^&]*)(&|$)"), i = window.location.search.substr(1).match(e);
                return null != i ? unescape(i[2]) : null
            }, set_param: function (t, e) {
                var i = location.search.substring(1), n = new RegExp("(^|&" + t + ")=[^&]*");
                return n.test(i) ? (i = i.replace(n, "$1=" + e), "?" + i) : "" == i ? "?" + t + "=" + e : "?" + i + "&" + t + "=" + e
            }, changeURLform: function () {
                var t;
                if (n.version.iqiyi ? t = "iqiyi_video_app" : n.version.weibo ? t = "weibo" : n.version.QQLive && (t = "QQLive"), t && !this.getURLlist("bsource") && !this.getURLlist("from")) {
                    var e = this.set_param("bsource", t);
                    window.history && history.replaceState && history.replaceState(null, null, "//" + location.host + location.pathname + e + location.hash)
                }
            }, identify: function () {
                var t, e = this.getURLlist("bsource") || this.getURLlist("from");
                if (e) t = e; else {
                    var a = document.referrer;
                    a && (a.indexOf("baidu.com") >= 0 || a.indexOf("so.com") >= 0 || a.indexOf("sogou.com") >= 0 || a.indexOf("google.com") >= 0 || a.indexOf("sm.cn") >= 0) ? t = "seo" : i.get("bsource") ? t = i.get("bsource") : n.version.mqq ? t = "qq" : n.version.weibo ? t = "weibo" : n.version.MicroMessenger ? t = "wechat" : n.version.mbaidu ? t = "bdbox" : n.version.iqiyi ? t = "iqiyi_video_app" : n.version.qq ? t = "qqbrowser_app" : n.version.uc ? t = "uc_browser_app" : n.version.QQLive && (t = "QQLive")
                }
                return t
            }
        }
    }, 7: function (t, exports) {
        t.exports = {
            getSec: function (t) {
                var e = 1 * t.substring(1, t.length), i = t.substring(0, 1);
                return "s" === i ? 1e3 * e : "h" === i ? 60 * e * 60 * 1e3 : "d" === i ? 24 * e * 60 * 60 * 1e3 : void 0
            }, set: function (t, e, i) {
                var n = this.getSec(i), a = new Date;
                a.setTime(a.getTime() + 1 * n), document.cookie = t + "=" + escape(e) + ";expires=" + a.toGMTString() + ";path=/"
            }, get: function (t) {
                var e = new RegExp("(^| )" + t + "=([^;]*)(;|$)"), i = document.cookie.match(e);
                return i ? unescape(i[2]) : null
            }, "delete": function (t) {
                var e = new Date;
                e.setTime(e.getTime() - 1);
                var i = this.get(t);
                null != i && (document.cookie = t + "=" + i + ";expires=" + e.toGMTString())
            }
        }
    }, 8: function (t, exports) {
        t.exports = {
            version: function () {
                var t = navigator.userAgent;
                navigator.appVersion;
                return {
                    mobile: /AppleWebKit.*Mobile.*/i.test(t),
                    ios: /\(i[^;]+;( U;)? CPU.+Mac OS X/i.test(t),
                    android: /Android/i.test(t) || /Linux/i.test(t),
                    windowsphone: /Windows Phone/i.test(t),
                    iPhone: /iPhone/i.test(t),
                    iPad: /iPad/i.test(t),
                    webApp: !/Safari/i.test(t),
                    MicroMessenger: /MicroMessenger/i.test(t),
                    weibo: /Weibo/i.test(t),
                    uc: /UCBrowser/i.test(t),
                    qq: /MQQBrowser/i.test(t),
                    baidu: /Baidu/i.test(t),
                    mqq: /QQ\/([\d\.]+)/i.test(t),
                    mbaidu: /baiduboxapp/i.test(t),
                    iqiyi: /iqiyi/i.test(t),
                    QQLive: /QQLive/i.test(t),
                    Safari: /Safari/i.test(t),
                    Youku: /youku/i.test(t)
                }
            }(), language: (navigator.browserLanguage || navigator.language).toLowerCase()
        }
    }, 12: function (t, exports) {
        !function () {
            $.ajax({url: "https://api.bilibili.com/x/web-interface/online"}).done(function (t) {
                if (0 === t.code) {
                    var e = t.data.region_count, i = 0;
                    for (var n in e) {
                        var a = Number(e[n]);
                        "177" === n || "11" === n || "23" === n ? isNaN(a) || (i += ~~a) : isNaN(a) ? $(".m-i[data-tid=" + n + "]").find("span").text("--") : (a = ~~a, $(".m-i[data-tid=" + n + "]").find("span").text(a > 999 ? "999+" : a))
                    }
                    i > 0 ? $(".m-i[data-tid=23]").find("span").text(i > 999 ? "999+" : i) : $(".m-i[data-tid=23]").find("span").text("--")
                }
            })
        }()
    }, 54: function (t, exports, e) {
        var i = e(5);
        $(function () {
            var t = {
                0: {title: "鏇存柊鏃堕棿", desc: 2, asc: 3},
                1: {title: "杩界暘浜烘暟", desc: 0, asc: 1},
                2: {title: "寮€鎾椂闂�", desc: 4, asc: 5}
            };
            $("body").on("click", ".v_sort.tab_sort a", function (e) {
                var n = $(e.currentTarget), a = n.attr("data-value"), r = n.hasClass("asc") ? "asc" : "desc";
                i.report({click: "sort"}, {sort_id: t[a][r] + 1})
            }).on("click", ".special_selector .right a", function () {
                var t = $.trim($(this).closest(".special_mbox").find(".left").text());
                i.report({click: "filter"}, {title: t + "-" + $(this).data("value")})
            }).on("click", ".v_ul li a", function () {
                var t = $(this).closest("li");
                i.report({click: "works"}, {
                    season_id: t.attr("data-season_id"),
                    title: $(this).attr("title"),
                    ord_id: t.index() + 1
                })
            }).on("click", ".reset-btn", function () {
                i.report({click: "reset"})
            }), $(".n_num li").on("click", "a", function () {
                var t = $(this).parents("li"), e = t.index();
                5 == e ? i.report({click: "timeline"}) : e < 5 && i.report({click: "subarea"}, {
                        ord_id: e + 1,
                        url: i.parseUrl($(this).attr("href") || "")
                    })
            })
        })
    }, 55: function (t, exports) {
        !function ($, t) {
            "use strict";
            function e(t, e, i) {
                var n = t.children(), r = !1;
                t.empty();
                for (var s = 0, o = n.length; s < o; s++) {
                    var l = n.eq(s);
                    if (t.append(l), i && t.append(i), a(t, e)) {
                        l.remove(), r = !0;
                        break
                    }
                    i && i.detach()
                }
                return r
            }

            function i(t, e, r, s, o) {
                var l = !1,
                    d = "a, table, thead, tbody, tfoot, tr, col, colgroup, object, embed, param, ol, ul, dl, blockquote, select, optgroup, option, textarea, script, style",
                    c = "script, .dotdotdot-keep";
                return t.contents().detach().each(function () {
                    var u = this, h = $(u);
                    if ("undefined" == typeof u)return !0;
                    if (h.is(c)) t.append(h); else {
                        if (l)return !0;
                        t.append(h), !o || h.is(s.after) || h.find(s.after).length || t[t.is(d) ? "after" : "append"](o), a(r, s) && (l = 3 == u.nodeType ? n(h, e, r, s, o) : i(h, e, r, s, o)), l || o && o.detach()
                    }
                }), e.addClass("is-truncated"), l
            }

            function n(t, e, i, n, s) {
                var d = t[0];
                if (!d)return !1;
                var u = l(d), h = u.indexOf(" ") !== -1 ? " " : "銆€", p = "letter" == n.wrap ? "" : h, f = u.split(p),
                    g = -1, v = -1, w = 0, m = f.length - 1;
                if (n.fallbackToLetter && 0 === w && 0 === m && (p = "", f = u.split(p), m = f.length - 1), n.maxLength) u = r(u.trim().substr(0, n.maxLength), n), o(d, u); else {
                    for (; w <= m && (0 !== w || 0 !== m);) {
                        var b = Math.floor((w + m) / 2);
                        if (b == v)break;
                        v = b, o(d, f.slice(0, v + 1).join(p) + n.ellipsis), i.children().each(function () {
                            $(this).toggle().toggle()
                        }), a(i, n) ? (m = v, n.fallbackToLetter && 0 === w && 0 === m && (p = "", f = f[0].split(p), g = -1, v = -1, w = 0, m = f.length - 1)) : (g = v, w = v)
                    }
                    if (g == -1 || 1 === f.length && 0 === f[0].length) {
                        var _ = t.parent();
                        t.detach();
                        var y = s && s.closest(_).length ? s.length : 0;
                        if (_.contents().length > y ? d = c(_.contents().eq(-1 - y), e) : (d = c(_, e, !0), y || _.detach()), d && (u = r(l(d), n), o(d, u), y && s)) {
                            var x = s.parent();
                            $(d).parent().append(s), $.trim(x.html()) || x.remove()
                        }
                    } else u = r(f.slice(0, g + 1).join(p), n), o(d, u)
                }
                return !0
            }

            function a(t, e) {
                return t.innerHeight() > e.maxHeight || e.maxLength && t.text().trim().length > e.maxLength
            }

            function r(t, e) {
                for (; $.inArray(t.slice(-1), e.lastCharacter.remove) > -1;)t = t.slice(0, -1);
                return $.inArray(t.slice(-1), e.lastCharacter.noEllipsis) < 0 && (t += e.ellipsis), t
            }

            function s(t) {
                return {width: t.innerWidth(), height: t.innerHeight()}
            }

            function o(t, e) {
                t.innerText ? t.innerText = e : t.nodeValue ? t.nodeValue = e : t.textContent && (t.textContent = e)
            }

            function l(t) {
                return t.innerText ? t.innerText : t.nodeValue ? t.nodeValue : t.textContent ? t.textContent : ""
            }

            function d(t) {
                do t = t.previousSibling; while (t && 1 !== t.nodeType && 3 !== t.nodeType);
                return t
            }

            function c(t, e, i) {
                var n, a = t && t[0];
                if (a) {
                    if (!i) {
                        if (3 === a.nodeType)return a;
                        if ($.trim(t.text()))return c(t.contents().last(), e)
                    }
                    for (n = d(a); !n;) {
                        if (t = t.parent(), t.is(e) || !t.length)return !1;
                        n = d(t[0])
                    }
                    if (n)return c($(n), e)
                }
                return !1
            }

            function u(t, e) {
                return !!t && ("string" == typeof t ? (t = $(t, e), !!t.length && t) : !!t.jquery && t)
            }

            function h(t) {
                for (var e = t.innerHeight(), i = ["paddingTop", "paddingBottom"], n = 0, a = i.length; n < a; n++) {
                    var r = parseInt(t.css(i[n]), 10);
                    isNaN(r) && (r = 0), e -= r
                }
                return e
            }

            if (!$.fn.dotdotdot) {
                $.fn.dotdotdot = function (t) {
                    if (0 === this.length)return $.fn.dotdotdot.debug('No element found for "' + this.selector + '".'), this;
                    if (this.length > 1)return this.each(function () {
                        $(this).dotdotdot(t)
                    });
                    var n = this, r = n.contents();
                    n.data("dotdotdot") && n.trigger("destroy.dot"), n.data("dotdotdot-style", n.attr("style") || ""), n.css("word-wrap", "break-word"), "nowrap" === n.css("white-space") && n.css("white-space", "normal"), n.bind_events = function () {
                        return n.bind("update.dot", function (t, s) {
                            switch (n.removeClass("is-truncated"), t.preventDefault(), t.stopPropagation(), typeof o.height) {
                                case"number":
                                    o.maxHeight = o.height;
                                    break;
                                case"function":
                                    o.maxHeight = o.height.call(n[0]);
                                    break;
                                default:
                                    o.maxHeight = h(n)
                            }
                            o.maxHeight += o.tolerance, "undefined" != typeof s && (("string" == typeof s || "nodeType" in s && 1 === s.nodeType) && (s = $("<div />").append(s).contents()), s instanceof $ && (r = s)), f = n.wrapInner('<div class="dotdotdot" />').children(), f.contents().detach().end().append(r.clone(!0)).find("br").replaceWith("  <br />  ").end().css({
                                height: "auto",
                                width: "auto",
                                border: "none",
                                padding: 0,
                                margin: 0
                            });
                            var d = !1, c = !1;
                            return l.afterElement && (d = l.afterElement.clone(!0), d.show(), l.afterElement.detach()), a(f, o) && (c = "children" == o.wrap ? e(f, o, d) : i(f, n, f, o, d)), f.replaceWith(f.contents()), f = null, $.isFunction(o.callback) && o.callback.call(n[0], c, r), l.isTruncated = c, c
                        }).bind("isTruncated.dot", function (t, e) {
                            return t.preventDefault(), t.stopPropagation(), "function" == typeof e && e.call(n[0], l.isTruncated), l.isTruncated
                        }).bind("originalContent.dot", function (t, e) {
                            return t.preventDefault(), t.stopPropagation(), "function" == typeof e && e.call(n[0], r), r
                        }).bind("destroy.dot", function (t) {
                            t.preventDefault(), t.stopPropagation(), n.unwatch().unbind_events().contents().detach().end().append(r).attr("style", n.data("dotdotdot-style") || "").removeClass("is-truncated").data("dotdotdot", !1)
                        }), n
                    }, n.unbind_events = function () {
                        return n.unbind(".dot"), n
                    }, n.watch = function () {
                        if (n.unwatch(), "window" == o.watch) {
                            var t = $(window), e = t.width(), i = t.height();
                            t.bind("resize.dot" + l.dotId, function () {
                                e == t.width() && i == t.height() && o.windowResizeFix || (e = t.width(), i = t.height(), c && clearInterval(c), c = setTimeout(function () {
                                    n.trigger("update.dot")
                                }, 100))
                            })
                        } else d = s(n), c = setInterval(function () {
                            if (n.is(":visible")) {
                                var t = s(n);
                                d.width == t.width && d.height == t.height || (n.trigger("update.dot"), d = t)
                            }
                        }, 500);
                        return n
                    }, n.unwatch = function () {
                        return $(window).unbind("resize.dot" + l.dotId), c && clearInterval(c), n
                    };
                    var o = $.extend(!0, {}, $.fn.dotdotdot.defaults, t), l = {}, d = {}, c = null, f = null;
                    return o.lastCharacter.remove instanceof Array || (o.lastCharacter.remove = $.fn.dotdotdot.defaultArrays.lastCharacter.remove), o.lastCharacter.noEllipsis instanceof Array || (o.lastCharacter.noEllipsis = $.fn.dotdotdot.defaultArrays.lastCharacter.noEllipsis), l.afterElement = u(o.after, n), l.isTruncated = !1, l.dotId = p++, n.data("dotdotdot", !0).bind_events().trigger("update.dot"), o.watch && n.watch(), n
                }, $.fn.dotdotdot.defaults = {
                    ellipsis: "... ",
                    wrap: "word",
                    fallbackToLetter: !0,
                    lastCharacter: {},
                    tolerance: 0,
                    callback: null,
                    after: null,
                    height: null,
                    watch: !1,
                    windowResizeFix: !0,
                    maxLength: null
                }, $.fn.dotdotdot.defaultArrays = {
                    lastCharacter: {
                        remove: [" ", "銆€", ",", ";", ".", "!", "?"],
                        noEllipsis: []
                    }
                }, $.fn.dotdotdot.debug = function (t) {
                };
                var p = 1, f = $.fn.html;
                $.fn.html = function (e) {
                    return e != t && !$.isFunction(e) && this.data("dotdotdot") ? this.trigger("update", [e]) : f.apply(this, arguments)
                };
                var g = $.fn.text;
                $.fn.text = function (e) {
                    return e != t && !$.isFunction(e) && this.data("dotdotdot") ? (e = $("<div />").text(e).html(), this.trigger("update", [e])) : g.apply(this, arguments)
                }
            }
        }(jQuery), jQuery(document).ready(function ($) {
            $(".dot-ellipsis").each(function () {
                var t = $(this).hasClass("dot-resize-update"), e = $(this).hasClass("dot-timer-update"), i = 0,
                    n = $(this).attr("class").split(/\s+/);
                $.each(n, function (t, e) {
                    var n = e.match(/^dot-height-(\d+)$/);
                    null !== n && (i = Number(n[1]))
                });
                var a = {};
                e && (a.watch = !0), t && (a.watch = "window"), i > 0 && (a.height = i), $(this).dotdotdot(a)
            })
        }), jQuery(window).on("load", function () {
            jQuery(".dot-ellipsis.dot-load-update").trigger("update.dot")
        }), "object" == typeof t.exports && (t.exports = {
            dotdotdot: function (t, e) {
                return "string" == typeof t ? $(t).dotdotdot(e) : void t.dotdotdot(e)
            }
        })
    }
});