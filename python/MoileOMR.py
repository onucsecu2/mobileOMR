import cv2
from imutils.perspective import four_point_transform
from imutils import contours
import math
import imutils
import numpy as np
image = cv2.imread('/home/onu/Desktop/Untitled Folder/16.jpg')
template = cv2.imread('/home/onu/Desktop/Untitled Folder/mcq.png')
correct_ans=['_','A','B','C','A','A','A','C','C','D','D','B','B','A','D','B','B','D','A','B','B']

def get_ans(n):

    pos=math.ceil(n/5)
    ans=pos%4
    switcher = {
        0: "D",
        1: "A",
        2: "B",
        3: "C",
    }
    return switcher.get(ans, "nothing")
def get_q_num(n):
    pos=math.ceil(n/20)
    ans=n%5
    if ans==0:
        ans=5
    return (pos-1)*5+ans
def warp(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    blurred = cv2.GaussianBlur(gray, (9,9 ), 0)
    edged = cv2.Canny(blurred, 70, 200)
    cnts = cv2.findContours(edged.copy(), cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
    cnts = imutils.grab_contours(cnts)
    docCnt = None

    # ensure that at least one contour was found
    if len(cnts) > 0:
        # sort the contours according to their size in
        # descending order
        cnts = sorted(cnts, key=cv2.contourArea, reverse=True)

        # loop over the sorted contours
        for c in cnts:
            # approximate the contour
            peri = cv2.arcLength(c, True)
            approx = cv2.approxPolyDP(c, 0.02 * peri, True)

            # if our approximated contour has four points,
            # then we can assume we have found the paper
            if len(approx) == 4:
                docCnt = approx
                break

    paper = four_point_transform(image, docCnt.reshape(4, 2))
    warped = four_point_transform(gray, docCnt.reshape(4, 2))
    return warped
# warped=warp(template)

def takeSecond(elem):
    return elem[1]
def sort_circles(circles):
    j=0
    scircles=[]
    tmp=[]

    for i in circles:
        j=j+1
        tmp.append(i)
        if(j%5==0):
            # tmp=sorted(tmp[0], key=lambda k: k[1])
            tmp.sort(key=takeSecond)
            for p in tmp:
                scircles.append(p)

            del tmp[:]
    return scircles



def find_circles(image,image2):
    gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    blurred = cv2.GaussianBlur(gray, (15, 15), 0)
    circles = cv2.HoughCircles(blurred,cv2.HOUGH_GRADIENT,1,40,
                                param1=40,param2=30,minRadius=40,maxRadius=70)

    circles = np.uint16(np.around(circles))
    circles1=sorted(circles[0], key=lambda k: k[0])
    circles2=sort_circles(circles1)
    print(get_q_num(80),get_ans(80))
    # print(circles2)
    # j=0
    # for i in circles2:
    #     # j=j+1
    #     # draw the outer circle
    #     # print(i)
    #     cv2.circle(image2,(i[0],i[1]),i[2],(0,255,0),2)
    #     # draw the center of the circle
    #     cv2.circle(image2,(i[0],i[1]),2,(0,0,255),3)

        # if(j==12):
        #     cv2.circle(image2, (i[0], i[1]), i[2], (0, 255, 128), 5)
        #     # draw the center of the circle
        #     cv2.circle(image2, (i[0], i[1]), 2, (0, 0, 255), 3)
        #     break

        # cv2.imwrite('result.jpg', res)
    return image2,circles2

res,circles=find_circles(image,image)
def contour_img(image,circles):
    gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    blurred = cv2.GaussianBlur(gray, (13, 13), 0)
    thresh = cv2.threshold(blurred, 95, 255,cv2.THRESH_BINARY_INV)[1]
    global q
    q=0
    for i in circles:
        q=q+1
        Q = get_q_num(q)
        A = get_ans(q)

        if(A==correct_ans[Q]):
            # print(Q,"(",A,")")
            cv2.circle(image, (i[0], i[1]), i[2], (255, 0, 0), 15)
        global w
        global b
        w = 0
        b = 0
        for x in range(i[0]-i[2],i[0]+i[2]):
            for y in range(i[1]-i[2],i[1]+i[2]):
                c=thresh[y,x]
                if(c>0):
                    w=w+1
                else:
                    b=b+1
        total=b+w
        ratio=(w/total)*100
        if ratio>50:
            # cv2.circle(image, (i[0], i[1]), i[2], (199, 110,255 ), 5)
            print(get_q_num(q),get_ans(q),q)
            if(correct_ans[Q]==A):
                cv2.circle(image, (i[0], i[1]), i[2], (0, 255, 0), 5)
            else:
                cv2.circle(image, (i[0], i[1]), i[2], (0, 0, 255), 5)

        # break
        # cv2.circle(thresh, (i[0], i[1]), i[2], (255,120, 0), 3)
    return image

res=contour_img(image,circles)
cv2.namedWindow("output", cv2.WINDOW_NORMAL)
# imS = cv2.resize(image, (960, 540))
cv2.imshow('output',res)
# cv2.imwrite('result.jpg', res)
k=cv2.waitKey(0)
if(k==27):
    exit(0)
cv2.destroyAllWindows()