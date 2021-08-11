package com.martins.valet.data.features.transaction.cloud.rest;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.data.features.entity.disk.AgreementRest;
import com.martins.valet.data.features.entity.rest.ClientResponse;
import com.martins.valet.data.features.entity.rest.TicketRest;
import com.martins.valet.data.features.entity.rest.TransactionOutRest;
import com.martins.valet.data.features.entity.rest.VehycleResponse;
import com.martins.valet.data.features.entity.rest.reports.ReportAgreementRest;
import com.martins.valet.data.features.entity.rest.reports.ReportVehycleRest;
import com.martins.valet.data.features.entity.rest.reports.ReportsRevenuewRest;
import com.martins.valet.data.features.transaction.cloud.TransactionDataSourceCloud;
import com.martins.valet.data.helpers.rest.DefaultServiceError;
import com.martins.valet.data.helpers.rest.ValetRestServiceRetrofit;
import com.martins.valet.domain.features.mappers.rest.AgreementRestMapper;
import com.martins.valet.domain.features.mappers.rest.ClientRestMapper;
import com.martins.valet.domain.features.mappers.rest.VehycleResponseMapper;
import com.martins.valet.domain.features.model.Agreement;
import com.martins.valet.domain.features.model.ReportAgreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.Vehycle;
import com.martins.valet.domain.features.model.VehycleInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by policante on 7/13/16.
 */
public class TransactionDataServiceRetrofit extends ValetRestServiceRetrofit implements TransactionDataSourceCloud {

    private TransactionRestService getService() {
        return getService(TransactionRestService.class);
    }

    @Override
    public Observable<VehycleInfo> findVehycleInfoFromPlate(String token, String plate) {
        return getService().findVehycleFromPlate(token, plate).flatMap(new Func1<JsonElement, Observable<VehycleInfo>>() {
            @Override
            public Observable<VehycleInfo> call(JsonElement jsonElement) {
                if (jsonElement.getAsJsonObject().get("code").getAsInt() == 200) {

                    VehycleInfo info = new VehycleInfo();
                    info.setVehycle(null);
                    info.setClient(null);
                    info.setExistsTransactionOpen(false);

                    JsonObject jsonObject = jsonElement.getAsJsonObject().getAsJsonObject("data");
                    if (jsonObject.has("veiculo") && jsonObject.get("veiculo").isJsonObject()) {
                        Gson gson = new Gson();
                        VehycleResponse vehycle = gson.fromJson(jsonObject.get("veiculo").toString(), VehycleResponse.class);
                        info.setVehycle(new VehycleResponseMapper().dataToModel(vehycle));
                        info.setExistsTransactionOpen(false);

                        if (jsonObject.has("cliente") && jsonObject.get("cliente").isJsonObject()) {
                            ClientResponse client = gson.fromJson(jsonObject.get("cliente").toString(), ClientResponse.class);
                            info.setClient(new ClientRestMapper().dataToModel(client));
                        }

                    }
                    return Observable.just(info);
                } else {
                    String message = jsonElement.getAsJsonObject().get("message").getAsString();
                    return Observable.error(new Exception(message));

                }
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends VehycleInfo>>() {
            @Override
            public Observable<? extends VehycleInfo> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.d("FindPlate", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                return Observable.error(throwable);
            }
        });
    }

    @Override
    public Observable<Transaction> createTransaction(String token, final Transaction transaction) {
        HashMap<String, String> map = new HashMap<>();
        map.put("placa", transaction.getVehycle().getPlate());
        map.put("marca", transaction.getVehycle().getBrand());
        map.put("tipo", transaction.getVehycle().getType());
        map.put("cor", transaction.getVehycle().getColor());
        map.put("dataentrada", DateHelper.getDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getTransactionIn()));

        return getService().transactionIn(token, map).flatMap(new Func1<JsonObject, Observable<Transaction>>() {
            @Override
            public Observable<Transaction> call(JsonObject json) {
                if (json.get("data").isJsonNull()) {
                    String mensagem = "Erro ao efetuar transação.";
                    if (!json.get("message").isJsonNull()) {
                        mensagem = json.get("message").getAsString();
                    }
                    return Observable.error(new Exception(mensagem));
                }

                JsonObject data = json.getAsJsonObject("data");

                if (data.has("transacao") && data.get("transacao").isJsonObject()) {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

                    TicketRest ticketRest = gson.fromJson(data.getAsJsonObject("transacao").toString(), TicketRest.class);

                    if (ticketRest != null) {

                        Vehycle vehycle = new Vehycle();
                        vehycle.setPlate(ticketRest.getVehycle().getPlate());
                        vehycle.setColor(ticketRest.getVehycle().getColor());
                        vehycle.setBrand(ticketRest.getVehycle().getBrand());
                        vehycle.setType(ticketRest.getVehycle().getType());

                        Transaction ticket = new Transaction();
                        ticket.setIdentifier(ticketRest.getTicket());
                        ticket.setTransactionIn(ticketRest.getCreated());
                        ticket.setVehycle(vehycle);

                        return Observable.just(ticket);
                    }
                }

                return Observable.empty();

            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends Transaction>>() {
            @Override
            public Observable<? extends Transaction> call(Throwable throwable) {

                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("transaction", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(throwable);
            }
        });
    }

    @Override
    public Observable<Transaction> findTransactionInfoFromPlateOrID(String token, String plateOrId, String convenio) {

        Map<String, String> map = new HashMap<>();

        if (TextUtils.isDigitsOnly(plateOrId)) {
            map.put("id", plateOrId);
        } else {
            map.put("placa", plateOrId);
        }

        map.put("convenio", convenio != null ? convenio : "0");

        return getService().findTransactionFromPlate(token, map).flatMap(new Func1<JsonElement, Observable<TransactionOutRest>>() {
            @Override
            public Observable<TransactionOutRest> call(JsonElement jsonElement) {

                if (jsonElement != null) {

                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

                    TransactionOutRest rest = gson.fromJson(jsonElement.getAsJsonObject().get("data").toString(), TransactionOutRest.class);
                    if (jsonElement.getAsJsonObject().get("code").getAsInt() == 202) {
                        rest.setHasProcessed(true);
                    }

                    return Observable.just(rest);
                } else {
                    return Observable.error(new Exception("Nenhum veículo encontrado"));
                }
            }
        }).flatMap(new Func1<TransactionOutRest, Observable<Transaction>>() {
            @Override
            public Observable<Transaction> call(TransactionOutRest rest) {

                if (rest == null) {
                    return Observable.error(new Exception("Nenhum dado encontrado"));
                }

                Vehycle vehycle = new Vehycle();
                vehycle.setPlate(rest.getPlate());
                vehycle.setType(rest.getType());
                vehycle.setBrand(rest.getBrand());
                vehycle.setColor(rest.getColor());

                Transaction transaction = new Transaction();
                transaction.setIdentifier(rest.getTicket());
                transaction.setVehycle(vehycle);
                transaction.setTransactionIn(rest.getCreated());
                transaction.setTransactionOut(rest.getFinish());
                transaction.setValueParcial(rest.getValueParcial());
                transaction.setValueTotal(rest.getValueTotal());
                transaction.setTimeTotal(rest.getTime());
                transaction.setAgreement(rest.getAgreement());
                transaction.setHasProcessed(rest.isHasProcessed());

                return Observable.just(transaction);
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends Transaction>>() {
            @Override
            public Observable<? extends Transaction> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("transaction", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(throwable);
            }
        });
    }

    @Override
    public Observable<Transaction> closeTransaction(String token, Transaction transaction) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", transaction.getIdentifier());
        map.put("valor", String.valueOf(transaction.getValueParcial()));
        map.put("tempo", String.valueOf(transaction.getTimeTotal()));
        map.put("convenio", TextUtils.isEmpty(transaction.getAgreement()) ? "0" : transaction.getAgreement());
        map.put("datasaida", DateHelper.getDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getTransactionOut()));

        return getService().transactionOut(token, map).flatMap(new Func1<TransactionOutRest, Observable<Transaction>>() {
            @Override
            public Observable<Transaction> call(TransactionOutRest rest) {

                if (rest == null) {
                    return Observable.just(null);
                }

                Vehycle vehycle = new Vehycle();
                vehycle.setPlate(rest.getPlate());
                vehycle.setType(rest.getType());
                vehycle.setBrand(rest.getBrand());
                vehycle.setColor(rest.getColor());

                Transaction transaction = new Transaction();
                transaction.setIdentifier(rest.getTicket());
                transaction.setVehycle(vehycle);
                transaction.setTransactionIn(rest.getCreated());
                transaction.setTransactionOut(rest.getFinish());
                transaction.setValueParcial(rest.getValueParcial());
                transaction.setValueTotal(rest.getValueTotal());
                transaction.setTimeTotal(rest.getTime());
                transaction.setAgreement(rest.getAgreement());

                return Observable.just(transaction);
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<Transaction>>() {
            @Override
            public Observable<Transaction> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("transactionOut", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(new Exception("Não foi possível concluir a transação"));
            }
        });
    }

    @Override
    public Observable<List<Transaction>> getTransactionOpen(String token) {
        return getService().getTransactionOpen(token).flatMap(new Func1<List<TransactionOutRest>, Observable<List<Transaction>>>() {
            @Override
            public Observable<List<Transaction>> call(List<TransactionOutRest> listRest) {
                if (listRest == null) {
                    return Observable.error(new Exception("Nenhum dado encontrado"));
                }

                List<Transaction> list = new ArrayList<>();

                for (TransactionOutRest rest : listRest) {
                    Vehycle vehycle = new Vehycle();
                    vehycle.setPlate(rest.getPlate());
                    vehycle.setType(rest.getType());
                    vehycle.setBrand(rest.getBrand());
                    vehycle.setColor(rest.getColor());

                    Transaction transaction = new Transaction();
                    transaction.setIdentifier(rest.getTicket());
                    transaction.setVehycle(vehycle);
                    transaction.setTransactionIn(rest.getCreated());
                    transaction.setTransactionOut(rest.getFinish());
                    transaction.setValueParcial(rest.getValueParcial());
                    transaction.setValueTotal(rest.getValueTotal());
                    transaction.setTimeTotal(rest.getTime());
                    transaction.setAgreement(rest.getAgreement());
                    transaction.setMensal(rest.getMensal() == 1);

                    list.add(transaction);
                }

                return Observable.just(list);
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Transaction>>>() {
            @Override
            public Observable<? extends List<Transaction>> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("transactionOpen", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(throwable);
            }
        });
    }

    @Override
    public Observable<List<Agreement>> getAgreements(String token) {
        return getService().getAgreement(token).flatMap(new Func1<List<AgreementRest>, Observable<List<Agreement>>>() {
            @Override
            public Observable<List<Agreement>> call(List<AgreementRest> agreementRests) {
                List<Agreement> list = new ArrayList<>();
                for (AgreementRest rest : agreementRests) {
                    list.add(new AgreementRestMapper().dataToModel(rest));
                }
                return Observable.just(list);
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Agreement>>>() {
            @Override
            public Observable<? extends List<Agreement>> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);

                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("getAgreements", error.getMessage());

                        return Observable.error(new Exception(error.getMessage()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(throwable);
            }
        });
    }

    @Override
    public Observable<Transaction> reopenTransaction(String token, Transaction transaction) {
        return getService().reopenTransaction(token, transaction.getIdentifier()).flatMap(new Func1<TransactionOutRest, Observable<Transaction>>() {
            @Override
            public Observable<Transaction> call(TransactionOutRest rest) {

                if (rest == null) {
                    return Observable.error(new Exception("Nenhum dado encontrado"));
                }

                Vehycle vehycle = new Vehycle();
                vehycle.setPlate(rest.getPlate());
                vehycle.setType(rest.getType());
                vehycle.setBrand(rest.getBrand());
                vehycle.setColor(rest.getColor());

                Transaction transaction = new Transaction();
                transaction.setIdentifier(rest.getTicket());
                transaction.setVehycle(vehycle);
                transaction.setTransactionIn(rest.getCreated());
                transaction.setTransactionOut(rest.getFinish());
                transaction.setValueParcial(rest.getValueParcial());
                transaction.setValueTotal(rest.getValueTotal());
                transaction.setTimeTotal(rest.getTime());
                transaction.setAgreement(rest.getAgreement());

                return Observable.just(transaction);
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends Transaction>>() {
            @Override
            public Observable<? extends Transaction> call(Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException http = ((HttpException) throwable);
                    try {
                        DefaultServiceError error = DefaultServiceError.prepareError(http.response());
                        Log.e("reopen", error.getMessage());
                        return Observable.error(new Exception(error.getMessage()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return Observable.error(new Exception("Nenhuma movimentação encontrada"));
            }
        });
    }

    @Override
    public Observable<ReportsRevenuew> getReportRevenuew(String token, Date dateIn, Date dateOut) {

        String strIn = "";
        String strOut = "";

        try {
            strIn = DateHelper.convertStringFromDateTime(dateIn, "yyyy-MM-dd HH:mm:ss");
            strOut = DateHelper.convertStringFromDateTime(dateOut, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getService().getRevenue(token, strIn, strOut).flatMap(new Func1<ReportsRevenuewRest, Observable<ReportsRevenuew>>() {
            @Override
            public Observable<ReportsRevenuew> call(ReportsRevenuewRest revenue) {

                ReportVehycleRest vehycle = revenue.getVehycle();

                ReportsRevenuew reports = new ReportsRevenuew();
                reports.getAvulso().setQuantity(vehycle.getAvulso().getQuantity());
                reports.getMensal().setQuantity(vehycle.getMensal().getQuantity());
                reports.getGeneral().setQuantity(vehycle.getGeneral().getQuantity());
                reports.getGeneral().setValueBegin(vehycle.getGeneral().getValueBegin());
                reports.getGeneral().setValueFinal(vehycle.getGeneral().getValueFinal());
                reports.getGeneral().setValueTotal(vehycle.getGeneral().getValueTotal());
                reports.getOpen().setQuantity(vehycle.getOpen().getQuantity());

                for (ReportAgreementRest rest : vehycle.getAgreements()) {
                    reports.getAgreements().add(new ReportAgreement(rest.getAgreement(), rest.getQuantity()));
                }

                return Observable.just(reports);
            }
        });
    }

}
