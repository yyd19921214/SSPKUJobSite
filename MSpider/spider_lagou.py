# coding:utf-8
# 从拉勾网获取公司信息
import requests
import json
import util
import time


def parseLagouCom():
    rtnList = []
    url = 'https://www.lagou.com/gongsi/0-0-0.json'
    payload = {}
    # payload['sortField'] = 1

    for i in range(1, 10):
        payload['pn'] = i
        try:
            print('parse num:{0}'.format(i))
            r = requests.get(url, data=payload, timeout=60)
            if r and r.status_code == 200:
                print(r.content)
                t = json.loads(r.content.decode('utf-8'))
                for com in t['result']:
                    res = {}
                    res['id'] = com['companyId']
                    res['FullName'] = com['companyFullName']
                    res['ShortName'] = com['companyShortName']
                    res['city'] = com['city']
                    res['industry'] = com['industryField']
                    res['stage'] = com['financeStage']
                    res['desc'] = com['companyFeatures']
                    rtnList.append(res)
                time.sleep(3)
        except Exception as e:
            print(e)

    # util.write_list(rtnList, 'company/lagou_com_total_21_30.txt')


def parser_job_in_city(city_code):
    mcookie = {"user_trace_token": "20170423105434-04aa7712ccb647ada89b69707c75572d"}
    for i in range(1,2):
        r = requests.post(url='https://www.lagou.com/gongsi/{0}-0-0.json'.format(city_code), data={'pn': i, 'sortField': 0, 'havemark': 0},cookies=mcookie)
        res=json.loads(str(r.content,encoding='utf-8'))
        if 'result' in res and len(res['result'])>0:
            print(res['result'][0])
        # print(res)



if __name__ == '__main__':
    headers = {}
    parser_job_in_city(3)
               # "Origin": "https://www.lagou.com",
               # "Host": "www.lagou.com"}
               # "User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36"}
    # mcookie={"user_trace_token":"20170423105434-04aa7712ccb647ada89b69707c75572d"}
    # r=requests.post(url='https://www.lagou.com/gongsi/3-0-0.json',data={'pn':2,'sortField':0,'havemark':0},cookies=mcookie)
    #
    # print(str(r.content,encoding='utf-8'))
    # for _ in range(5):
    #     r=requests.post(url="https://www.lagou.com/jobs/companyAjax.json?needAddtionalResult=false",data={"kd":"阿里巴巴"},cookies=mcookie)
    #     print(str(r.content,encoding='utf-8'))
    # parseLagouCom()
