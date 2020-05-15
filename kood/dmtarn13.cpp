#include <iostream>
#include <set>
#include <map>
#include <vector>
#include <queue>

/**
 * Sisend kujul:
 * [tippude arv] [servade arv] servade arv korda: ([i-nda serva lähtetipp] [i-nda serva sihttipp] [i-nda serva kaal])
 *
 * Väljund kujul:
 * servade arv ridu kujul: [i-nda serva lähtetipp] [i-nda serva sihttipp] [i-nda serva kaal]
 */


using namespace std;
int main(){
    // tippude arv:
    int n;
    cin >> n;
    // Servade arv:
    int m;
    cin >> m;
    // Servad:
    map<int, map<int, int> > servad;
    int tagurpidi = -1;
    for (int i=0; i<m; i++){
        int a,b,c;
        cin >> a >> b >> c;
        servad[b][a] = c;
        if (a>b){
            tagurpidi = a;
        }
    }
    // Leian tsüklid (kõik tsüklid peavad sisaldama tagurpidi serva) ning eemaldan igast tsüklist kõige halvema serva:
    uusTsykkel:{};
    queue<int> q;
    q.push(tagurpidi);
    set<int> vaadeldud;
    map<int,vector<int> > teekonnad;
    while (!q.empty()){
        int node = q.front();
        q.pop();
        for (map<int,int>::iterator iter = servad[node].begin(); iter != servad[node].end(); iter++){
            if (iter->first == tagurpidi) {
                // Olen leidnud tsükli
                teekonnad[node].push_back(tagurpidi);
                int eelmine = tagurpidi;
                int maxScore = -2147483648;
                pair<int,int> maxServ;
                for (vector<int>::iterator iter2 = teekonnad[node].begin(); iter2 != teekonnad[node].end(); iter2++){
                    int tsykliservaKaal = servad[eelmine][*iter2];
                    int minKaal = 2147483647;
                    int minNode = -1;
                    for (map<int,int>::iterator iter3 = servad[eelmine].begin(); iter3 != servad[eelmine].end(); iter3++){
                        if (iter3->second < minKaal && iter3->first != *iter2){
                            minKaal = iter3->second;
                            minNode = iter3->first;
                        }
                    }
                    // Näitab, kui palju toesepuu kaal väheneb, kui välise serva asemel eemaldada tsükliserv
                    int score = tsykliservaKaal - minKaal;
                    if (minNode != -1 && score > maxScore){
                        maxScore = score;
                        maxServ = make_pair(eelmine, *iter2);
                    }
                    eelmine = *iter2;
                }
                servad[maxServ.first].erase(maxServ.second);
                goto uusTsykkel;
            } else if (!vaadeldud.count(iter->first)) {
                vaadeldud.insert(iter->first);
                q.push(iter->first);
                teekonnad[node].push_back(iter->first);
                teekonnad[iter->first] = teekonnad[node];
                teekonnad[node].pop_back();
            }
        }
    }
    
    // Alles on ilma suunatud tsükliteta graaf, eemaldan muud tsüklid:
    for (map<int, map<int, int> >::iterator iter = servad.begin(); iter != servad.end(); iter++){
        int minKaal = 2147483647;
        int minNode;
        for (map<int,int>::iterator iter2 = iter->second.begin(); iter2 != iter->second.end(); iter2++){
            if (iter2->second < minKaal){
                minKaal = iter2->second;
                minNode = iter2->first;
            }
        }
        for (map<int,int>::iterator iter2 = iter->second.begin(); iter2 != iter->second.end(); iter2++){
            if (iter2->first != minNode){
                iter->second.erase(iter2);
            }
        }
    }
    
    // Prindin puu välja:
    for (map<int, map<int, int> >::iterator iter = servad.begin(); iter != servad.end(); iter++){
        for (map<int,int>::iterator iter2 = iter->second.begin(); iter2 != iter->second.end(); iter2++){
            cout << iter2->first << " " << iter->first << '\n';
        }
    }
}