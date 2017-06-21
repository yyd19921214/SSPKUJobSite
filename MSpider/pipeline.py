__author__ = 'yangyudong'
import DBHelper
import random
import spider_baidu
import time
import re
DataRangeRegex='(\d+)-(\d+)$'
ExpMaxRegex='(\d+)年以下'
ExpMinRegex='(\d+)年以上'

# def processSalary(salary):
#     salary=salary.strip()
#     m = re.match(SalaryRegex, salary)
#     if m

def processFullTimeJob(job):
    job['postTime'] = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    try:
        if job.get('requireDegree')==None:
            job['requireDegree'] = 0 #如果要求学历空缺,则默认为学历不限
        elif '大专' in job['requireDegree'] or '专科' in job['requireDegree']:
            job['requireDegree'] = 1
        elif '本科' in job['requireDegree']:
            job['requireDegree'] = 2
        elif '硕士' in job['requireDegree'] or '研究生' in job['requireDegree']:
            job['requireDegree']=3
        elif '博士' in job['requireDegree']:
            job['requireDegree']=4
        else:
            print('无法处理学位字段:{0}'.format(job['requireDegree']))
            job['requireDegree'] = 0  # 如果要求学历空缺,则默认为学历不限
    except:
        job['requireDegree'] = 0

    salary=job.get('salary',None)
    try:
        if salary!=None:
            salary=salary.rstrip('元,/月')
            m = re.match(DataRangeRegex, salary)
            if m:
                fields=salary.split('-')
                job['minSalary']=int(fields[0])//1000
                job['maxSalary']=int(fields[1])//1000
            elif '以上' in salary:
                salary=salary.rstrip('以上')
                job['minSalary'] = int(salary) // 1000
            elif '以下' in salary:
                salary=salary.rstrip('以下')
                job['maxSalary']=int(salary) // 1000
            else: #无法处理设置成面议
                print('无法处理工资字段:{0}'.format(salary))
        else:
            print('工资字段为空值')
    except:
        pass
    del job['salary']

    experience=job.get('experience',None)
    try:
        if experience!=None:
            experience=experience.rstrip('年')
            m=re.match(DataRangeRegex,experience)
            if m:
                fields = experience.split('-')
                job['minExperience'] = int(fields[0])
                job['maxExperience'] = int(fields[1])
            elif re.match(ExpMaxRegex,experience):
                job['maxExperience'] = str(m.groups()[0])
            elif re.match(ExpMinRegex,experience):
                job['minExperience'] = str(m.groups()[0])
            else:
                print('无法处理工作经验字段:{0}'.format(experience))
        else:
            print('经验字段为空值')
    except:
        pass
    del job['experience']
    job['workType'] = 2


def processPartTimeJob(job):
    job['postTime'] = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    try:
        if job.get('requireDegree')==None:
            job['requireDegree'] = 0 #如果要求学历空缺,则默认为学历不限
        elif '大专' in job['requireDegree'] or '专科' in job['requireDegree']:
            job['requireDegree'] = 1
        elif '本科' in job['requireDegree']:
            job['requireDegree'] = 2
        elif '硕士' in job['requireDegree'] or '研究生' in job['requireDegree']:
            job['requireDegree']=3
        elif '博士' in job['requireDegree']:
            job['requireDegree']=4
        else:
            print('无法处理学位字段:{0}'.format(job['requireDegree']))
            job['requireDegree'] = 0  # 如果要求学历空缺,则默认为学历不限
    except:
        job['requireDegree'] = 0

    salary = job.get('salary', None)
    try:
        if salary != None:
            salary = salary.rstrip('元,/月')
            m = re.match(DataRangeRegex, salary)
            if m:
                fields = salary.split('-')
                job['minSalary'] = int(fields[0]) // 1000
                job['maxSalary'] = int(fields[1]) // 1000
            elif '以上' in salary:
                salary = salary.rstrip('以上')
                job['minSalary'] = int(salary) // 1000
            elif '以下' in salary:
                salary = salary.rstrip('以下')
                job['maxSalary'] = int(salary) // 1000
            else:  # 无法处理设置成面议
                print('无法处理工资字段:{0}'.format(salary))
        else:
            print('工资字段为空值')
    except:
        pass
    del job['salary']
    job['workType'] = 1

if __name__=='__main__':
    dbh=DBHelper.DBHelper()
    comList_1=[]#存储著名公司列表
    comList_2=[]#存储随机选取的小公司列表
    comList_3=[]#存储实习工作公司列表
    idSt=set()
    with open('company/famous.txt','r',encoding='utf-8')as f:
        for line in f:
            line=line.rstrip()
            com=dbh.selectCompanyByName(line)
            if com!=None:
                comList_1.append(com)
                idSt.add(com[0])

    otherComList=list(dbh.selectCompany(limitNum=500))
    while(len(comList_2)<200):
        o=random.choice(otherComList)
        if o[0] in idSt:
            continue
        else:
            idSt.add(o[0])
            comList_2.append(o)

    while(len(comList_3)<50):
        o = random.choice(otherComList)
        if o[0] in idSt:
            continue
        else:
            idSt.add(o[0])
            comList_3.append(o)

    print('famous companys number is:{0}'.format(len(comList_1)))
    print('random companys number is:{0}'.format(len(comList_2)))
    print('intern companys number is:{0}'.format(len(comList_3)))

    jobList_1 = spider_baidu.parseJob4CompanyList(comList_1,5)
    jobList_2=spider_baidu.parseJob4CompanyList(comList_2,2)
    jobList_3 = spider_baidu.parseJob4CompanyList(comList_3, 2)
    for job in jobList_1:
        if dbh.checkJobIfExist(job['companyId'],job['jobName']):
            jobList_1.remove(job)
        else:
            processFullTimeJob(job)
            print(job)
    # existCnt=0
    for job in jobList_2:
        if dbh.checkJobIfExist(job['companyId'],job['jobName']):
            jobList_2.remove(job)
        else:
            processFullTimeJob(job)
    for job in jobList_3:
        if dbh.checkJobIfExist(job['companyId'],job['jobName']):
            jobList_3.remove(job)
        else:
            processPartTimeJob(job)

    print('the remain number of list_1 is:{0}'.format(len(jobList_1)))
    print('the remain number of list_2 is:{0}'.format(len(jobList_2)))
    print('the remain number of list_3 is:{0}'.format(len(jobList_3)))


    dbh.insertJob(jobList_1)
    dbh.insertJob(jobList_2)
    dbh.insertJob(jobList_3)

