import util
import DBHelper
import time
import random

def mergeComJob(comPath,jobPath):
    comList=util.read_list(comPath)
    jobList=util.read_list(jobPath)
    jobDict={}
    for job in jobList:
        if job['companyId'] not in jobDict:
            jobDict[job['companyId']]=[]
        jobDict[job['companyId']].append(job)



    return comList,jobDict

def mergeInDB(comPath,jobPath):
    dbh=DBHelper.DBHelper()
    comList,jobDict=mergeComJob(comPath,jobPath)

    for idx,com in enumerate(comList):
        try:
            r1=dbh.selectCompanyByName(com['ShortName'])
            if r1!=None:
                comId_db=r1[0]
            else:
                processComData(com)
                comId_db = dbh.insertCompany(com)

            if com['id'] in jobDict:
                jobs=jobDict[com['id']]
                for job in jobs:
                    job['companyId']=comId_db
                    processJobData(job)
                    if dbh.checkJobIfExist(comId_db,job['jobName'])==True:
                        jobs.remove(job)
                dbh.insertJob(jobs)
        except Exception as e:
            processComData(e)
        print(idx)

def processJobData(job):
    job['postTime'] = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    job['requireDegree'] = random.randint(1, 3)
    job['minSalary'] = random.randint(8, 12)
    job['maxSalary'] = job['minSalary'] + random.randint(1, 4)
    job['minExperience'] = random.randint(1, 5)
    job['maxExperience'] = job['minExperience'] + random.randint(1, 2)
    job['workType'] = 2


def processComData(com):
    com['type'] = random.randint(1, 3)


if __name__=='__main__':
    dbh = DBHelper.DBHelper()
    jobList=util.read_list('jobs/jobs_1_31.txt')
    for job in jobList:
        processJobData(job)
    dbh.insertJob(jobList)
    # print(len(jobList))
    # mergeInDB('company/lagou_com_total.txt', 'jobs/lagou_jobs_100.txt')
    # mergeInDB('company/lagou_com_total.txt', 'jobs/lagou_jobs_200.txt')
    # mergeInDB('company/lagou_com_total.txt', 'jobs/lagou_jobs_400-600.txt')







# mergeInDB('lagou_com.txt','lagou_com_jobs.txt')
