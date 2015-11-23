package org.stellar.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.stellar.base.Keypair;
import org.stellar.sdk.effects.Effect;
import org.stellar.sdk.operations.Operation;

class GsonSingleton {
  private static Gson instance = null;

  protected GsonSingleton() {}

  public static Gson getInstance() {
    if (instance == null) {
      TypeToken accountPageType = new TypeToken<Page<Account>>() {};
      TypeToken effectPageType = new TypeToken<Page<Effect>>() {};
      TypeToken ledgerPageType = new TypeToken<Page<Ledger>>() {};
      TypeToken operationPageType = new TypeToken<Page<Operation>>() {};
      TypeToken transactionPageType = new TypeToken<Page<Transaction>>() {};

      instance = new GsonBuilder()
                      .registerTypeAdapter(Keypair.class, new KeypairTypeAdapter().nullSafe())
                      .registerTypeAdapter(Operation.class, new OperationDeserializer())
                      .registerTypeAdapter(Effect.class, new EffectDeserializer())
                      .registerTypeAdapter(accountPageType.getType(), new PageDeserializer<Account>(accountPageType))
                      .registerTypeAdapter(effectPageType.getType(), new PageDeserializer<Account>(effectPageType))
                      .registerTypeAdapter(ledgerPageType.getType(), new PageDeserializer<Ledger>(ledgerPageType))
                      .registerTypeAdapter(operationPageType.getType(), new PageDeserializer<Operation>(operationPageType))
                      .registerTypeAdapter(transactionPageType.getType(), new PageDeserializer<Transaction>(transactionPageType))
                      .create();
    }
    return instance;
  }

}
