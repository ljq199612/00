
    " >> ###############################################################################################
    "                    -------------------- 安装和初始化 --------------------
    " 方式一. 存在自建库
    "         软链接 ln -s  ~/public/gitRepo/00/editor/vim/.vim*  ~
    " 方式二
    " 下载vim-plug插件管理工具
    "      curl -fLo ~/.vim/autoload/plug.vim --create-dirs  https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim

    " >> 注意：
    "   问题：文件树图标乱码：
    "   原因：vim-devicons 文件数图标插件需要对应字体
    "   解决：
    "       方式一：下载字体,操作系统安装使用该字体
    "           ~/public/gitRepo/00/editor/vim/font/Droid Sans Mono Nerd Font Complete.otf
    "       方式二：注释掉该插件
    " >> ###############################################################################################

    " >> ###############################################################################################
    "                       -------------------- 快捷键 --------------------

    let mapleader="\<Space>"

    " >>
        " 1 <C-g> 显示文件绝对路径
        

    " >>
        nmap  <Leader>fed  :e ~/.vimrc<CR>
        nmap  <Leader>fer  :e ~/public/
        nmap  <Leader>ab   :abclear<CR>
        nmap  <Leader>fo : call OpenbyDolphin()<CR>
        func! OpenbyDolphin()
           call system("dolphin" . matchstr(expand('%:p'),'.*/')
        endfunc

        nmap  <Leader>ds   :1,$s///gc

        nmap <leader>s :w!<cr>
        set pastetoggle=<F11>                                      " 切换到粘贴模式
        map <silent> <Leader>ed :%s/\s\+$//g<CR>gg                 " 删除行未<Space><Tab>

    " >> emacs 基础操作
        imap  <C-b>         <Esc>ha
        imap  <C-n>         <Esc>ja
        imap  <C-p>         <Esc>ka
        imap  <C-f>         <Esc>la

        nmap  <C-a>          0
        nmap  <C-e>          $
        nmap  <C-k>          D
        nmap  <C-u>          d0
        imap  <C-a>         <Esc>0i
        imap  <C-e>         <Esc>$a
        imap  <C-k>         <Esc>lDa
        imap  <C-u>         <Esc>d0xi

        " >> 需要先在 bashrc 中添加 stty -ixon , 用以注销<C-s>挂起等
        nmap  <C-x><C-s>    :w!<CR>
        imap  <C-x><C-s>    <Esc>:w!<CR>


    " [t]>>  toggle
        map <Leader>tr :set norelativenumber ! relativenumber?<CR>       " 相对行号
        map <Leader>tl :set nowrap ! wrap?<CR>                           " 折行
        map <Leader>tt :set noignorecase ! ignorecase?<CR>               " 搜索时大小写

        " toggle <Space>和<Tab>
        map <Leader>t<Tab> : call ToggleTabSpace()<CR>
        func! ToggleTabSpace()
                exec "set noexpandtab! expandtab?"
                exec "%retab!"
        endfunc

    " [b]>>  buffer
        nmap <Leader>bb  :bNext<CR>                         " 切换buffer
        nmap <Leader>bd  :bwipe<CR>                         " 删除buffer

    " [w]>>  窗口
        nmap <Leader>w-  :split<cr>                         " 左右分屏
        nmap <Leader>w/  :vsplit<cr>                        " 上下分屏
        nmap <Leader>wd  ZZ                                 " 关闭选中屏幕
        nmap <Leader>w0  :only<cr>                          " 只保留选中屏幕
        nmap <Leader>ww  <C-W>w                             " 快速切换屏幕
        nmap <Leader>wh  <C-W><C-H>                         " 切换到左屏幕
        nmap <Leader>wj  <C-W><C-J>                         " 切换到下屏幕
        nmap <Leader>wk  <C-W><C-K>                         " 切换到上屏幕
        nmap <Leader>wl  <C-W><C-L>                         " 切换到右屏幕

    " [m]>>  markdown
        " 替换 #### 为 h4 
        nmap <Leader>ch  :%s/^#### \(.*\)/<h4 class = 'auto-sort-sub1'>\1<\/h4>\r/gc
        
        map <Leader>ms : call AddSection()<CR>
        func! AddSection()
            call append(line("."), "<div class = 'data-section default-folding'>")  "光标当前位置添加一行
            call append(line(".")+1, "<h2 class = 'section-title'><label class = 'block-number'></label></h2>")
            call append(line(".")+2, "<div class = 'folding-area'>")
            call append(line(".")+3, "")
            call append(line(".")+4, '</div>')
            call append(line(".")+5, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+2, 29, 0])
        endfunc

        map <Leader>mhb : call AddBlockNumber()<CR>
        func! AddBlockNumber()
            call append(line("."), "<label class = 'block-number'></label>")

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+1, 30, 0])
        endfunc

        map <Leader>mhh : call AddHeaderSub()<CR>
        func! AddHeaderSub()
            call append(line("."), "<h3 class = 'auto-sort-sub'></h3>")

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+1, 28, 0])
        endfunc

        map <Leader>mhs : call AddHeaderSub1()<CR>
        func! AddHeaderSub1()
            call append(line("."), "<h4 class = 'auto-sort-sub1'></h4>")

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+1, 29, 0])
        endfunc

        map <Leader>mi : call AddImage()<CR>
        func! AddImage()
            call append(line("."), '<div class="myImage">')
            call append(line(".")+1, "")
            call append(line(".")+2, "![-image-]()")
            call append(line(".")+3, "")
            call append(line(".")+4, '<label class="imageTitle">图示 </label>')
            call append(line(".")+5, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 12, 0])
        endfunc

        map <Leader>mf : call AddFormula()<CR>
        func! AddFormula()
            call append(line("."), '<div class="myFormula">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc

        map <Leader>ma : call AddAnswer()<CR>
        func! AddAnswer()
            call append(line("."), '<div class="myAnswer">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc
		
        map <Leader>mp : call AddProblem()<CR>
        func! AddProblem()
            call append(line("."), '<div class="myProblem">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc

        map <Leader>me : call AddExample()<CR>
        func! AddExample()
            call append(line("."), '<div class="myExample">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc

        map <Leader>mn : call AddNote()<CR>
        func! AddNote()
            call append(line("."), '<div class="myNote">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc

        map <Leader>mw : call AddWarning()<CR>
        func! AddWarning()
            call append(line("."), '<div class="myWarning">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc

        map <Leader>mt : call AddTip()<CR>
        func! AddTip()
            call append(line("."), '<div class="myTip">')  "光标当前位置添加一行
            call append(line(".")+1, "")
            call append(line(".")+2, "")
            call append(line(".")+3, '</div>')

            let pos = getpos(".")      "鼠标位置
            call setpos(".", [0, pos[1]+3, 3, 0])
        endfunc


    " 插件>>{
        " >> [f]>>  nerdtreee   文件树
            map <Leader>ft  :NERDTreeToggle<CR>
            map <Leader>ff  :NERDTreeFind<CR>              " 定位文件
            "
        " >> LeaderF      查找     https://github.com/Yggdroot/LeaderF
            map <Leader>pp :LeaderfFile<CR>
            map <Leader>pf :LeaderfFunction<CR>
            map <Leader>pb :LeaderfBuffer<CR>
            " 最近使用过的文件
            map <Leader>pr :LeaderfMru<CR>
            " 所有主题色
            map <Leader>pc :LeaderfColorscheme<CR>

        " >> Tabularize
            map <Leader>a& :Tabularize /&<CR>
            map <Leader>a= :Tabularize /=<CR>
            map <Leader>a: :Tabularize /:<CR>
            map <Leader>a" :Tabularize /"<CR>
            map <Leader>a' :Tabularize /'<CR>
            map <Leader>a:: :Tabularize /:\zs<CR>
            map <Leader>a, :Tabularize /,<CR>
            map <Leader>a,, :Tabularize /,\zs<CR>

        " >> MarkdownPreview
            nmap <silent> <F8> <Plug>MarkdownPreview
            nmap <silent> <F9> <Plug>StopMarkdownPreview

    " }
    " >> ###############################################################################################

    " 基本{
        " >> 光标
            let &t_SR = "\e[1 q"                " SR = REPLACE mode        1. 闪烁块条  2. 块
            let &t_EI = "\e[2 q"                " EI = NORMAL INSERT mode  3. 闪烁底线  4. 底线
            let &t_SI = "\e[5 q"                " SI = INSERT mode         5. 闪烁条    6. 条
        " >> 通用
            set nocompatible                    " [nocp] 去除VI一致性
            set helplang=cn

            set number                          " 显示行号
            set ru                              " 显示状态栏
            set wildmode=longest,list,full      " tab自动匹配格式
            set scrolloff=2                     " 上下可视行数
            set ignorecase                      " 输入命令时忽略大小写匹配
            set incsearch                       " 实时搜索
            set hlsearch                        " [hls] 搜索时高亮显示被找到的文本
            set foldcolumn=0                    " 左侧留白
            set cursorline                      " 突出显示当前行
            set nowrap                          " 不自动换行
            syntax on                           " 自动语法高亮
            filetype on                         " 检测文件类型
            filetype plugin indent on           " 加载vim自带和插件相应的语法和文件类型相关脚本
            filetype plugin on                  " 检测文件类型插件
            set pastetoggle=<F10>               " 按对应的键切换到粘贴模式
            set history=124
            set nobackup                        " 覆盖文件时不备份
            set autochdir                       " 自动切换当前目录为当前文件所在的目录
            "set smartcase
            "set nowrapscan                      " 搜索到文件两端时不重新搜索
            "set noerrorbells                    " 关闭错误声音
            "set novisualbell
            "set t_vb=
            set lazyredraw                       " Don't redraw while executing macros (good performance config)
            "set magic                           " For regular expressions turn magic on
            "set showmatch                       " Show matching brackets when text indicator is over them
            "set novisualbell                    " No annoying sound on errors set noerrorbells
            "set mat=2                           " How many tenths of a second to blink
            set hidden                          " 允许在有未保存的修改时切换缓冲区，此时的修改由 vim 负责保存
            "set smartindent                     " 智能自动缩进
            set cmdheight=1                     " 设定命令行的行数为 1
            set list                            " 显示Tab符
            set listchars=tab:\|\ ,trail:.,extends:>,precedes:<
            set shortmess=atl                   " 启动时不显示捐赠提示
        " >> 编码
            set encoding=utf-8
            set langmenu=zh_CN.UTF-8
            language message zh_CN.UTF-8        " 系统提示编码为utf-8
            set fileencodings=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936   " [fencs]
            set ambiwidth=double                " 防止特殊符号无法正常显示
        " >> tab, backspace
            set tabstop=4                          " [ts] 设定 tab 长度为 4
            set softtabstop=4                   " 设置按BackSpace的时候可以一次删除掉4个空格
            set shiftwidth=4                     " 设定 << 和 >> 命令移动时的宽度为 4
            set smarttab                         " Be smart when using tabs
            set backspace=indent,eol,start      " 不设定在插入状态无法用退格键和 Delete 键删除回车符
            set whichwrap+=<,>,h,l              " 指定的符号操作到行末能跳到下一行
        " >> 拼写，换行，括号
            "set spell                          " 按z= 会出现拼写建议，按]s 可以直接跳到下一个拼写错误处
            "set tw=78                          " 断行设置
            "set lbr                            " 断行时保持单词完整在一行
            set fo+=mb                         " 断行对亚洲语言支持，B表示两行合一行时中间不补空格
            set display=lastline              " 解决自动换行格式下, 如高度在折行之后超过窗口高度结果这一行看不到的问题
            set showmatch                      " [sm] 显示括号配对情况
            set matchtime=5                    " 匹配括号高亮的时间（单位是十分之一秒）
    " }


    " 开发编程{
        " 文件自动加入头部信息{
            "新建.c,.h,.sh,.java文件，自动插入文件头
            autocmd BufNewFile *.cpp,*.[ch],*.sh,*.java exec ":call SetTitle()"
            func SetTitle()
                "如果文件类型为.sh文件
                if &filetype == 'sh'
                    call setline(1,          "\#########################################################################")
                    call append(line("."),   "\# File Name: ".expand("%"))
                    call append(line(".")+1, "\# Author: ma6174")
                    call append(line(".")+2, "\# mail: ma6174@163.com")
                    call append(line(".")+3, "\# Created Time: ".strftime("%c"))
                    call append(line(".")+4, "\#########################################################################")
                    call append(line(".")+5, "\#!/bin/bash")
                    call append(line(".")+6, "")
                else
                    call setline(1,          "/*************************************************************************")
                    call append(line("."),   "    > File Name: ".expand("%"))
                    call append(line(".")+1, "    > Author: ma6174")
                    call append(line(".")+2, "    > Mail: ma6174@163.com ")
                    call append(line(".")+3, "    > Created Time: ".strftime("%c"))
                    call append(line(".")+4, " ************************************************************************/")
                    call append(line(".")+5, "")
                endif
                if &filetype == 'cpp'
                    call append(line(".")+6, "#include<iostream>")
                    call append(line(".")+7, "using namespace std;")
                    call append(line(".")+8, "")
                endif
                if &filetype == 'c'
                    call append(line(".")+6, "#include<stdio.h>")
                    call append(line(".")+7, "")
                endif
                "新建文件后，自动定位到文件末尾
                autocmd BufNewFile * normal G
            endfunc
        " }


    " vim-plug插件管理器{
        filetype off                  " 必须要添加
        call plug#begin('~/.vim/plugged')

        " >> 通用
            Plug  'mhinz/vim-startify'                              " vim启动设置
            Plug  'scrooloose/nerdtree'                             " 目录树
            Plug  'vim-airline/vim-airline'                         " 增强状态栏
            Plug  'flazz/vim-colorschemes'                          " 配色方案库
            Plug  'ryanoasis/vim-devicons'                          " 文件图标管理 (系统需要先配字体)
            Plug  'Yggdroot/LeaderF', { 'do': './install.sh' }      " 文件搜索，代替ctrlp.vim
            Plug  'godlygeek/tabular'                               " outline视图
            Plug  'tpope/vim-surround'                              " 标点，标签自动匹配和修改


        " >> markdown
            Plug 'plasticboy/vim-markdown'                          " markdown 语法高亮
                                                                    " 浏览器预览
            Plug 'iamcco/markdown-preview.nvim', { 'do': { -> mkdp#util#install() } }


        " >> 其它
            Plug  'scrooloose/nerdcommenter'                        " 注释和反注释
            "Plug  'Valloric/YouCompleteMe'
            Plug  'timothycrosley/isort'                            " Python 导入模块整理
            "Plug  'davldhalter/jedi-vim'                           " Python 编程提示
            "Plug  'w0rp/ale'                                       " 语法纠错,需vim8.0以上
            Plug  'skywind3000/asyncrun.vim'
            Plug 'tpope/vim-fugitive'                               " 执行Git命令
            "Plug  'chrissimpkins/codeface'

            Plug  'Python-mode/python-mode'
            "Plugin  'indentpython.vim'                             " Python代码自动缩进
            "Plugin  'nvie/vim-flake8'                              " PEP8代码风格检查
            """"
            """"""
            "Plug  'Xuyuanp/nerdtree-git-plugin'                     "
            "Plugin  'Syntastic'                                     " automatic syntax checking
            "Plug  'SimpylFold'                                      " 代码折叠
            "Plug  'taglist.vim'                                     " source code browser
            "Plug  'taglist-plus'
        call plug#end()
    " }


    " 插件配置>>{
        " vim-plug>> 插件管理

            " 常用命令:
            " :PluginList       - 列出所有已配置的插件
            " :PluginInstall     - 安装插件,追加 `!` 用以更新或使用 :PluginUpdate
            " :PluginSearch foo - 搜索 foo ; 追加 `!` 清除本地缓存
            " :PluginClean      - 清除未使用插件,需要确认; 追加 `!`自动批准移除未使用插件

            " 查阅 :h vim-plug 获取更多细节和wiki以及FAQ
            " 将你自己对非插件片段放在这行之后
            " --------------------------------------------------------------------------------

        " nerdtree>>    文件树    https://github.com/scrooloose/nerdtree/

        " colorscheme>>    主题色
            colorscheme space-vim-dark

        " LeaderF>>   文件查找  (ctrlP的替代，需要python支持) https://github.com/Yggdroot/LeaderF
            let g:Lf_WildIgnore = {
                \ 'dir': ['.svn','.git','.hg','.vscode','.wine','.deepinwine','.oh-my-zsh'],
                \ 'file': ['*.sw?','~$*','*.bak','*.exe','*.o','*.so','*.py[co]']
                \}

            let g:Lf_PreviewResult = {
            \ 'File': 0,
            \ 'Buffer': 0,
            \ 'Mru': 0,
            \ 'Tag': 0,
            \ 'BufTag': 1,
            \ 'Function': 1,
            \ 'Line': 0,
            \ 'Colorscheme': 1
            \}

        " airline>>
            let g:airline#extensions#tabline#enabled = 1

            if !exists('g:airline_symbols')
              let g:airline_symbols = {}
            endif

            let g:airline_left_sep = '»'
            "let g:airline_left_sep = '▶'
            let g:airline_right_sep = '«'
            "let g:airline_right_sep = '◀'
            let g:airline_symbols.crypt = '🔒'
            let g:airline_symbols.linenr = '☰'
            "let g:airline_symbols.linenr = '␊'
            "let g:airline_symbols.linenr = '␤'
            let g:airline_symbols.linenr = '¶'
            let g:airline_symbols.maxlinenr = ''
            "let g:airline_symbols.maxlinenr = '㏑'
            let g:airline_symbols.branch = '⎇'
            let g:airline_symbols.paste = 'ρ'
            "let g:airline_symbols.paste = 'Þ'
            "let g:airline_symbols.paste = '∥'
            let g:airline_symbols.spell = 'Ꞩ'
            let g:airline_symbols.notexists = '∄'
            let g:airline_symbols.whitespace = 'Ξ'

        " devicons>>

        " vim-markdown>>
            "au BufRead,BufNewFile *.{md,mdown,mkd,mkdn,markdown,mdwn}
            let g:vim_markdown_folding_disabled = 1


    " >>>>>>>>>>>>>>>>>>>
            " taglist{  " taglist-使用前电脑要安装ctag, 中文支持不好，不要用中文文件夹
                map <silent> <F3> : TlistToggle<CR>
                let Tlist_Ctags_Cmd='ctags'
                let Tlist_Show_One_File=1               "不同时显示多个文件的tag，只显示当前文件的
                let Tlist_WinWidt =28                   "设置taglist的宽度
                let Tlist_Exit_OnlyWindow=1             "如果taglist窗口是最后一个窗口，则退出vim
                let Tlist_Use_Right_Window=1            "在右侧窗口中显示taglist窗口
                "let Tlist_Use_Left_Windo =1             "在左侧窗口中显示taglist窗口
            " }

        " tagbar{   " taglist的替代品，更适合C++开发
            map <F4> : TagbarToggle<CR>             "快捷键设置
            let g:tagbar_ctags_bin='ctags'          "ctags程序的路径
            let g:tagbar_width=30                   "窗口宽度的设置
            autocmd BufReadPost *.cpp,*.c,*.h,*.hpp,*.cc,*.cxx call tagbar#autoopen() "如果是c语言的程序的话，tagbar自动开启
        " }


        " surround{}

        " python{
            let g:pymode_python = 'python3'                        " 设置语法检测为Python3, 默认为Python2

            au BufNewFile,BufRead *.py
            \ set tabstop=4
            \ set softtabstop=4
            \ set shiftwidth=4
            \ set textwidth=79
            \ set expandtab
            \ set autoindent
            \ set fileformat=unix




