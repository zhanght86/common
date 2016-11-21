/**
 * 邮件输入自动提示插件
 * $().mailTip({
 *    mails: [], 需要提示的邮箱列表
 *    afterselect： function(){}, 选择后的回调
 *    width: null, 提示框的宽度，如果传值则为所传入的值，否则为自动和文本框等宽
 *    offsettop: 0, 相对于默认向上偏移量
 *    offsetleft: 0, 相对于默认向上偏移量
 *    zindex: 1000 z-index值
 * });
 * css style
 * ul.mailtip{float: none;background-color: #fcfeff;list-style: none;border:1px solid #97bccc;border-radius: 0px 0px 10px 10px;overflow: hidden; border-top: none;}
 * ul.mailtip li p{line-height: 30px;cursor: pointer; margin: 0 9px; overflow: hidden; word-wrap: break-word; height: 30px;}
 * ul.mailtip li:last-child{border-radius: 0px 0px 10px 10px;}
 * ul.mailtip li.active{background: #eaeaea;}
 * ul.mailtip li.hover{background: #e7f6ff;}
 */
(function ($) {
    //字符串转正则函数
    function parseRegExp(pattern, attributes){
        var imp = /[\^\.\\\|\(\)\*\+\-\$\[\]\?]/g;
        var imp_c = {};
        imp_c['^'] = '\\^';
        imp_c['.'] = '\\.';
        imp_c['\\'] = '\\\\';
        imp_c['|'] = '\\|';
        imp_c['('] = '\\(';
        imp_c[')'] = '\\)';
        imp_c['*'] = '\\*';
        imp_c['+'] = '\\+';
        imp_c['-'] = '\\-';
        imp_c['$'] = '\$';
        imp_c['['] = '\\[';
        imp_c[']'] = '\\]';
        imp_c['?'] = '\\?';
        pattern = pattern.replace(imp, function (o){
            return imp_c[o];
        });
        return new RegExp(pattern, attributes);
    }

    //创建提示列表项
    var createLists = function (value, mails) {
        var lists = '';
        var hasAt = /@/.test(value);
        var regx = '';
        if (hasAt) {
            var arr = value.split('@');
            if (arr.length > 2) return lists;
            value = arr[0];
            regx = parseRegExp('@' + arr[1], 'i');
        }

        value = hasAt ? value.split('@')[0] : value;

        for (var i = 0, len = mails.length; i < len; i++) {
            if (hasAt && regx && !regx.test(mails[i])) continue;
            lists += '<li title="' + value + mails[i] + '" style="margin: 0; padding: 0; float: none;"><p>' + value + mails[i] + '</p></li>';
        }

        return lists.replace(/^<li([^>]*)>/, '<li$1 class="active">');
    };

    //改变列表激活状态
    var changeActive = function (panle, up) {
        //如果提示框隐藏跳出执行
        if (panle.css('display') === 'none') return;
        var liActive = panle.find('li.active');
        if (up) {
            var liPrev = liActive.prev();
            liPrev = liPrev.length ? liPrev : panle.find('li:last');
            liActive.removeClass('active');
            liPrev.addClass('active');
        } else {
            var liNext = liActive.next();
            liNext = liNext.length ? liNext : panle.find('li:first');
            liActive.removeClass('active');
            liNext.addClass('active');
        }
    };

    //展示隐藏提示
    var toggleTip = function (val, panle, mails) {
        //如果输入为空，带空格，中文字符，英文逗号，@开头，或者两个以上@直接隐藏提示
        if (!val || /[,]|[\u4e00-\u9fa5]|\s|^@/.test(val) | val.split('@').length > 2) {
            panle.hide();
        } else {
            var lists = createLists(val, mails);
            //如果返回的有列表项展开提示，否则隐藏。
            if (lists) {
                panle.html(lists).show();
            } else {
                panle.hide();
            }
        }
    };

    //调用接口
    $.fn.mailTip = function (config) {
        var defaults = {
            mails: ['@qq.com', '@163.com', '@sina.com', '@gmail.com', '@126.com', '@139.com', '@189.com', '@sohu.com', '@msn.com', '@hotmail.com', '@yahoo.com', '@yahoo.com.cn'],
            afterselect: $.noop,
            width: null,
            offsettop: 0,
            offsetleft: 0,
            zindex: 1000
        };

        config = $.extend({}, defaults, config);
        config.afterselect = typeof config.afterselect === 'function' ? config.afterselect : defaults.afterselect;
        config.width = typeof config.width === 'number' ? config.width : defaults.width;
        config.offsettop = typeof config.offsettop === 'number' ? config.offsettop : defaults.offsettop;
        config.offsetleft = typeof config.offsetleft === 'number' ? config.offsetleft : defaults.offsetleft;

        return this.each(function () {
            var mailInput = $(this);
            var parentPanle = mailInput.parent();
            //如果外层控件没有设置定位，就去给外层设置相对定位。
            !/absolute|relative/i.test(parentPanle.css('position')) && parentPanle.css('position', 'relative');
            //关闭自动完成
            mailInput.attr('autocomplete', 'off');

            //绑定keyup事件
            mailInput.bind('keyup',
                function (e) {
                    var target = $(this);
                    var listPanle = null;
                    var keyCode = e.keyCode;

                    //只在第一次按键时生成列表
                    if (!target.data('data-mailTip')) {
                        var offset = mailInput.offset();
                        var parentOffset = parentPanle.offset();

                        listPanle = $('<ul class="mailtip" style="display: none; float: none; position:absolute; margin: 0; padding: 0; z-index: ' + config.zindex + '"></ul>');

                        //放入DOM树
                        mailInput.after(listPanle);

                        listPanle.css({
                            top: offset.top - parentOffset.top + mailInput.outerHeight() + config.offsettop,
                            left: offset.left - parentOffset.left + config.offsetleft,
                            width: config.width || mailInput.outerWidth() - listPanle.outerWidth() + listPanle.width()
                        });

                        //绑定鼠标事件
                        listPanle.delegate('li', 'mouseenter mouseleave click', function (e) {
                            switch (e.type) {
                                case 'mouseenter':
                                    $(this).addClass('hover');
                                    break;
                                case 'click':
                                    var text = $(this).attr('title');
                                    mailInput.val(text).focus();
                                    config.afterselect.call(this, text);
                                    break;
                                case 'mouseleave':
                                    $(this).removeClass('hover');
                                    break;
                                default:
                                    break;
                            }
                        });

                        //点击其它地方关闭提示框
                        $(document).bind('click', function (e) {
                            if (e.target === mailInput[0]) return;
                            listPanle.hide();
                        });

                        target.data('data-mailTip', listPanle);
                    }

                    //获取提示框
                    listPanle = target.data('data-mailTip');
                    //获取文本框内容
                    var val = target.val();
                    //根据不同按键做处理
                    switch (keyCode) {
                        //tab键
                        case 9:
                            break;
                        //回车键
                        case 13:
                            break;
                        //向上键
                        case 38:
                            break;
                        //向下键
                        case 40:
                            break;
                        //默认
                        default:
                            toggleTip(val, listPanle, config.mails);
                            break;
                    }
                }).bind('keydown', function (e) {
                    var listPanle = $(this).data('data-mailTip');
                    //如果提示框不存在跳出
                    if (!listPanle) return;
                    //根据按键执行不同操作
                    switch (e.keyCode) {
                        //按tab键隐藏列表
                        case 9:
                            listPanle.hide();
                        //向上键
                        case 38:
                            changeActive(listPanle, true);
                            break;
                        //向下键
                        case 40:
                            changeActive(listPanle);
                            break;
                        //回车键
                        case 13:
                            //如果提示框隐藏跳出执行
                            if (listPanle.css('display') === 'none') return;
                            e.preventDefault();
                            var text = listPanle.find('li.active').attr('title');
                            mailInput.val(text).focus();
                            listPanle.hide();
                            config.afterselect.call(this, text);
                            break;
                        default:
                            break;
                    }
                });
        });
    };
})(jQuery);
