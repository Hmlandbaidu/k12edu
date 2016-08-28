#!/user/bin/python
# -*- coding:utf-8 -*-
import os
import sys
import shutil
import qrcode
import time

def exitsDir(path):         #判断是否存在文件
    return os.path.isdir(path)
    pass

def createDir(path):        #创建文件夹
    os.makedirs(path)
    pass

def copeFile(sourcePath,desPath):   #复制文件夹
    shutil.copy2(sourcePath,desPath)
    pass

def createqrCode(data,img_file):   #生成二维码
    #data = 'lanhongmei'
    #img_file = r'D:\py_qrcode.png'
    img = qrcode.make(data)
    # 图片数据保存至本地文件
    print(img_file)
    img.save(img_file)
    # 显示二维码图片
    #img.show()

if __name__ == '__main__':

    print("==================================")
    print(" merge java and aspectJ  v0.1     ")
    print("==================================")

    argc = len(sys.argv);
    if argc != 3:
        print("Parameter error");
        sys.exit(-1);
    srcPath=sys.argv[1];
    destPath=sys.argv[2];
    print("srcPath="+srcPath +",destPath="+destPath)

    if exitsDir(destPath):
            print("the file exists!")
    else:
            print("the file doesn't exists")
            createDir(destPath)

    copeFile(srcPath,destPath)

    createqrCode(destPath,destPath+"/qr_"+str(int(time.time()))+".png")
