#!/bin/bash
###############################
# author:   ljq
# date:     2019-05-30
# describe:
#   创建软件的桌面图标
###############################

echo "请输入app的根目录: "
read appRootPath
echo "确定app的根目录是 ${appRootPath} （y or n)"
read isAppRootPath

while true; do
    if [ "${isAppRootPath}" == "y" ]; then
        break
    elif [ "${isAppRootPath}" == "n" ]; then
        echo "请输入app的根目录: "
        read appRootPath
        echo "确定app的根目录是 ${appRootPath} (y or n)"
        read isAppRootPath
    else
        echo "确定app的根目录是 ${appRootPath} (y or n)"
        read isAppRootPath
    fi
done

if [ "${appRootPath: -1}" != "/" ]; then
    appRootPath="${appRootPath}/"
    echo "$appRootPath"
fi

echo "请输入icon的位置(相对于app根目录）: "
read iconRelativePath


iconPath="${appRootPath}${iconRelativePath}"
while true; do
    if [ ! -f "${appRootPath}${iconRelativePath}" ]; then
        echo "找不到该文件，请重新输入icon的位置： "
        read iconRelativePath
    else
        break
    fi
done

echo "请输入app运行脚本的位置(相对于app根目录)： "
read scriptRelativePath

while true; do
    if [ -f "${appRootPath}${scriptRelativePath}" ]; then
        break
    else
        echo "找不到该文件，请重新输入： "
        read scriptRelativePath
    fi
done

iconName=${iconRelativePath##*/}
scriptName=${scriptRelativePath##*/}

echo "请输入app的名称： "
read appName
deskVars=("Desktop" "desktop" "桌面");
for desk in "${deskVars[@]}"; do
    deskDir="$HOME/${desk}";
    if [ -f "$HOME/${deskDir}/${appName}.desktop" ]; then
	      rm -f "$HOME/${desk}/${appName}.desktop"
    fi
done
if [ -f "$HOME/.local/share/applications/${appName}.desktop" ]; then
    rm -f "$HOME/.local/share/applications/${appName}.desktop"
fi
if [ ! -d "$HOME/.local/share/applications/" ]; then
    mkdir -p "$HOME/.local/share/applications" ;
fi

deskAppContent="[Desktop Entry]\n
Encoding=UTF-8\n
Version=1.0\n
Name=$appName\n
GenericName=$appName\n
Exec=\"${appRootPath}${scriptRelativePath}\"\n
Terminal=false\n
Icon=${appRootPath}${iconRelativePath}\n
Type=Application\n
Categories=Office;\n
StartupNotify=true\n
Comment=${appName}";

deskAppFile="$HOME/.local/share/applications/${appName}.desktop"
touch "${deskAppFile}"
echo -e ${deskAppContent} >> "${deskAppFile}"
chmod +x "${deskAppFile}"

echo "在applications创建图标成功"
echo "在桌面创建图标 （y or n）"
read isNeedDeskIcon
while true; do
    if [ "${isNeedDeskIcon}" == "n" ]; then
        break
    elif [ "${isNeedDeskIcon}" == "y" ]; then
        for desk in "${deskVars[@]}"; do
            if [ -d "$HOME/${desk}" ]; then
                touch "$HOME/${desk}/${appName}.desktop"
                echo -e ${deskAppContent} >> "$HOME/${desk}/${appName}.desktop"
                chmod +x "$HOME/${desk}/${appName}.desktop"
            fi
        done
        break
    else
        echo "在桌面创建图标 （y or n）"
        read isNeedDeskIcon
    fi
done
