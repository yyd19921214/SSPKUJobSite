#coding:utf-8
import json



def write_list(value, path):
    print ('write to: {0}'.format(path))
    f = open(path, 'w',encoding='utf-8')
    for ele in value:
        f.write(json.dumps(ele, ensure_ascii=False) + '\n')
    f.close()

def read_list(path):

    print('read to:{0}'.format(path))
    rtnList = []
    with open(path,'r',encoding='utf-8') as f:
        for line in f:
            t = json.loads(line)
            if t != None:
                rtnList.append(t)
    return rtnList

def createComTab(path):
    comList=read_list(path)
    content=''
    for ele in comList:
        line=''
        line=str(ele['id'])+'\t'+ele['FullName']+'\t'+ele['city']+'\t'+ele['desc']+'\t'+ele['industry']+'\n'
        content+=line
    with open('lagou_com_tab.txt','w',encoding='utf-8') as f:
        f.write(content)



def createJobTab(path):
    jobList=read_list(path)
    content=''
    for ele in jobList:
        try:

            line=str(ele['companyId'])+'\t'+ele['jobName']+'\t'+ele['jobaddress']+'\t'+ele['desc']+'\n'
            content+=line
        except:
            print(ele)
    with open('lagou_job_tab.txt','w',encoding='utf-8') as f:
        f.write(content)

def write2mongo(job):
    pass

if __name__=='__main__':
    with open('salary=0_0city=北京query=Javaeducation=本科11.json', 'r', ) as f:
        content=f.read();
        t=json.loads(content)
        print(t)

