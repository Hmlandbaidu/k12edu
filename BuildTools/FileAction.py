#!/usr/bin/python
# -*- coding: UTF-8 -*-
'''
Created on 2016-7-16

@author: v_lanhongmei

History
    v0.1 merge java and aspectJ  v0.1
'''
import os
import sys
import zipfile
import copy
import shutil
import qrcode

def createFile(path):     #写入文件
    f=open(path,'w')
    for i in range(0,10):
        f.write(str(i)+'\n')
    f.close()
pass

def writeFile(path,overwrite):     #写入文件
    if overwrite:
        f=open(path,'w+')
    else:
        f=open(path,'a+')
    for i in range(0,7):
        f.write(str(i)+"ceshiyixia"+'\n')
    f.close()
pass

def readFile(path):      #读取文件
	f = open(path,'r')
	try:
		all_the_text = f.read()
		print(all_the_text)
	finally:
		f.close()
pass

def renameFile(path,new):
	os.rename(path,new)
pass

def copeFile(sourcePath,destPath):
	shutil.copy2(sourcePath,destPath)
pass

def zip_dir(dirname,zipfilename):
    filelist = []
    if os.path.isfile(dirname):
        filelist.append(dirname)
    else :
        for root, dirs, files in os.walk(dirname):
            for name in files:
                filelist.append(os.path.join(root, name))

    zf = zipfile.ZipFile(zipfilename, "w", zipfile.zlib.DEFLATED)
    for tar in filelist:
        arcname = tar[len(dirname):]
        #print arcname
        zf.write(tar,arcname)
    zf.close()
pass

# def unzip_file(zipfilename, unziptodir):
#     if not os.path.exists(unziptodir): os.mkdir(unziptodir, 0777)
#     zfobj = zipfile.ZipFile(zipfilename)
#     for name in zfobj.namelist():
#         name = name.replace('\\','/')

#         if name.endswith('/'):
#             os.mkdir(os.path.join(unziptodir, name))
#         else:
#             ext_filename = os.path.join(unziptodir, name)
#             ext_dir= os.path.dirname(ext_filename)
#             if not os.path.exists(ext_dir) : os.mkdir(ext_dir,0777)
#             outfile = open(ext_filename, 'wb')
#             outfile.write(zfobj.read(name))
#             outfile.close()
# pass
# if __name__ == '__main__':
#     zip_dir(r'E:/python/learning',r'E:/python/learning/zip.zip')
#     unzip_file(r'E:/python/learning/zip.zip',r'E:/python/learning2')
