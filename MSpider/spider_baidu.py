import requests
from util import write_list
import DBHelper
import DataEtl
import json
url = 'http://zhaopin.baidu.com/api/async'
cityList=['北京','上海','广州','深圳','杭州','成都']


def parseJob4CompanyList(comList,pageNum):
    rtnList=[]
    payload = {}
    payload['sort_type'] = 1
    payload['rn'] = 30
    payload['detailmode'] = 'close'
    # try:
    for idx,com in enumerate(comList):

        if com==None:
            continue
        print('the number of company parsed is {0}'.format(idx))
        comId=com[0]
        payload['query']=com[1]
        payload['city']=com[2]
        for i in range(0, pageNum):
            payload['pn'] = payload['rn']*(i)
            try:
                r = requests.session().post(url, data=payload,timeout=20)
                t = json.loads(r.content.decode('utf-8'))
                if len(t['data']['data'].get('disp_data',[]))==0:
                    break
                else:
                    print(len(t['data']['data'].get('disp_data',[])))
                    for ele in t['data']['data'].get('disp_data',[]):
                        job={}
                        job['companyId']=comId
                        job['jobName']=ele['name']
                        job['jobaddress']=payload['city']
                        job['desc']=ele['description']
                        job['salary']=ele.get('salary',None)
                        job['experience']=ele.get('experience',None)
                        job['requireDegree']=ele.get('education',None)
                        rtnList.append(job)
            except Exception as e:
                print('error is:{0}'.format(com[1]))
            # time.sleep(2)
    return rtnList

def addJobOfCompanyName(companyName):
    dbh=DBHelper.DBHelper()
    com=dbh.selectCompanyByName(companyName)
    jobList=parseJob4CompanyList([com])
    for job in jobList:
        if not dbh.checkJobIfExist(job['companyId'], job['jobName']):
            DataEtl.processJobData(job)
        else:
            jobList.remove(job)
    dbh.insertJob(jobList)


if __name__=='__main__':
    dbh=DBHelper.DBHelper()
    # companySt=set()
    nameSt=set()
    comList=[]

    with open('company/famous.txt','r',encoding='utf-8') as f:
        for line in f:
            line=line.strip('\r\n')
            comList.append(dbh.selectCompanyByName(line))
    # ori_List=dbh.selectCompany(limitNum=6000);
    # print(len(ori_List))
    # for com in ori_List:
    #     if com[1] in nameSt:
    #         continue
    #     else:
    #         comList.append(com)
    #         nameSt.add(com[1])
    # random.shuffle(comList)
    rtn=parseJob4CompanyList(comList)
    write_list(rtn,'jobs/jobs_1_31.txt')


    # addJobOfCompanyName('建设银行')
